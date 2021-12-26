package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.UserController;
import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.dtos.user.UserSaveDTO;
import com.mycompany.roadtripplanner.entities.Adress;
import com.mycompany.roadtripplanner.services.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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

    //On test le controller findAll
    @Test
    public void testFindAllUser() throws Exception{
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    //On test si l'utilisatuer n'existe pas
    @Test
    public void testFindOneUserWhereWrongId() throws Exception{
        //On execute la requete
        this.mockMvc.perform(get("/users/dvdcsdf5fs5sf"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindOne() throws Exception{
        //On crée un utilisateur
        UserDTO userDTO = this.userDto();
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
        Assertions.assertEquals(body.getFirstName(),this.userDto().getFirstName());

    }

    @Test
    public void testSaveUser() throws Exception {
        // on creer notre utilisateur
        UserDTO userDTO= this.userDto();
        //On initialise notre json pour la creation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        // on transforme notre obj en json
        String body = json.toJson(userDTO);
        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception{
        //On Initialise l'objet Gson pour la transformation
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String body = json.toJson(this.userDto());
        this.mockMvc.perform(delete("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateUser() throws Exception{
        //1 On recupère l'utilisateur
        UserDTO userDTO = this.userDto();
        UserDTO userUpdateDTo = this.userUPDATEDTO();
        BDDMockito.given(service.find("1"))
                .willReturn(Optional.of(userDTO));
        MvcResult result = this.mockMvc.perform(get("/users/1"))
                .andExpect((status().isOk()))
                .andReturn();
        Gson json  = new GsonBuilder().create();
        UserDTO body = json.fromJson(result.getResponse().getContentAsString(),UserDTO.class);

        BDDMockito.when(service.save(any(UserSaveDTO.class)))
                .thenReturn(userUpdateDTo);
        //2 On fait la modification
        body.setFirstName("THERY");
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();

        //2.5On transforme le résultat en objet
        UserDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), UserDTO.class);

        //3 On verifie si il a bien été modifié
        Assertions.assertEquals(finalBody.getFirstName(),this.userUPDATEDTO().getFirstName());
    }

    private UserDTO userDto(){
        return new UserDTO(
        "1",
        "THERY",
        "Jeremie",
        "thery.jeremie@yahoo.fr",
        "Jeremie1",
        new Date(),
        new Adress(),
        "123684IMAGE"
        );
    }
    private UserDTO userUPDATEDTO(){
        return new UserDTO(
                "1",
                "THERY",
                "nOELIE",
                "thery.jeremie@yahoo.fr",
                "Jeremie1",
                new Date(),
                new Adress(),
                "111114IMAGE"
        );
    }
}
