package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.UserController;
import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.dtos.user.UserUpdateDTO;
import com.mycompany.roadtripplanner.entities.Adress;
import com.mycompany.roadtripplanner.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    //Injection de dependance
    @Autowired
    private MockMvc mockMvc;

    //On copie le service
    @MockBean
    private UserService service;

    /**
     * Teste la route pour retrouver tous les users
     * @throws Exception
     */
    @Test
    public void testFindAllUser() throws Exception{
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Teste la route si un user n'existe pas
     * @throws Exception
     */
    @Test
    public void testFindOneUserWhereWrongId() throws Exception{
        //On execute la requete
        this.mockMvc.perform(get("/users/dvdcsdf5fs5sf"))
                .andExpect(status().isNotFound());
    }

    /**
     * Teste la route pour retrouver un user, avec son id
     * @throws Exception
     */
    @Test
    public void testFindOne() throws Exception{
        //On crée un utilisateur
        UserDTO userDTO = this.createUserDTOTest();
        //On appel la methode given pour moker notre service
        //en parametre le type de requete
        BDDMockito.given(service.find("1"))
                .willReturn (Optional.of(userDTO));
        MvcResult result = this.mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andReturn();
        //On initialisz l'objet Gson pour la transformation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        UserDTO body = json.fromJson(result.getResponse().getContentAsString(), UserDTO.class);
        Assertions.assertEquals(body.getFirstName(),this.createUserDTOTest().getFirstName());

    }

    /**
     * Teste la route pour créer un utilisateur
     * @throws Exception
     */
    @Test
    public void testSaveUser() throws Exception {
        // on creer notre cinema
        UserDTO userDTO= this.createUserDTOTest();
        //On initialise notre json pour la creation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        // on transforme notre obj en json
        String body = json.toJson(userDTO);
        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateUser() throws Exception{
        // Je créé deux unknownstage de test
        UserDTO unknownstageDTOTest = this.createUserDTOTest();
        UserDTO unknownstageDTOUpdate = this.updateUserDTOTest();

        // J'appelle BDDMockito pour simuler le unknownstage service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(unknownstageDTOTest));
        // Je teste la route qui permet de récupérer un unknownstage
        MvcResult result = this.mockMvc.perform(get("/users/5"))
                .andExpect(status().isOk())
                .andReturn();

        // Je transforme du format Json récupéré en unknownstageDTO
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        UserUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(), UserUpdateDTO.class);
        // Mockito peut prendre un objet de type UserSaveDTO
        BDDMockito.when(service.update(any(UserUpdateDTO.class)))
                .thenReturn(unknownstageDTOUpdate);

        // Qu'il va modifier
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme le format Json en UserDTO
        String result1 = resultUpdated.getResponse().getContentAsString();
        UserDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), UserDTO.class);
        // Je teste les assertions
        Assertions.assertEquals(finalBody.getFirstName(), this.updateUserDTOTest().getFirstName());
    }

    /**
     * Teste la route pour supprimer un utilisateur
     * @throws Exception
     */
    @Test
    public void testDeleteUser() throws Exception{
        //On Initialise l'objet Gson pour la transformation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String body = json.toJson(this.createUserDTOTest());
        this.mockMvc.perform(delete("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    private UserDTO createUserDTOTest(){
        return new UserDTO(
        "1",
        "THERY",
        "Jeremie",
        "thery.jeremie@yahoo.fr",
        "Jeremie1",
        new Date(),
        new Adress(),
        "123684IMAGE.jpg", 
        new ArrayList<>(),   
        new ArrayList<>()
        );
    }

    private UserDTO updateUserDTOTest(){
        return new UserDTO(
                "1",
                "dobro",
                "brice",
                "dobro.brice@yahoo.fr",
                "brice1",
                new Date(),
                new Adress(),
                "123684IMAGE.jpg",
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
