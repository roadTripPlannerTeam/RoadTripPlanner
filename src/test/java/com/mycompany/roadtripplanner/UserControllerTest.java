package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.UserController;
import com.mycompany.roadtripplanner.dtos.user.UserDTO;
import com.mycompany.roadtripplanner.entities.Adress;
import com.mycompany.roadtripplanner.entities.User;
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

import java.util.Date;
import java.util.Optional;

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
/*
    @Test
    public void testFindOne() throws Exception{
        //On crée un utilisateur
        UserDTO user = this.userDto();
        //On appel la methode given pour moker notre service
        //en parametre le type de requete
        BDDMockito.given(service.find("1"))
                .willReturn(Optional.of(user));
        MvcResult result = this.mockMvc.perform(get("users/1"))
                .andExpect(status().isOk())
                .andReturn();
        //On initialisz l'objet Gson pour la transformation
        Gson json =new GsonBuilder().create();
        UserDTO body = json.fromJson(
                result.getResponse().getContentAsString(),
                UserDTO.class
                );
        Assertions.assertEquals(body.getFirstName(),this.userDto().getFirstName());

    }*/

    @Test
    public void testSaveUser() throws Exception {
        // on creer notre cinema
        UserDTO userDTO= this.userDto();
        //On initialise notre json pour la creation
        Gson json = new GsonBuilder().create();
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
        Gson json = new GsonBuilder().create();
        String body = json.toJson(this.userDto());
        this.mockMvc.perform(delete("users"));
    }

    private UserDTO userDto(){
        return new UserDTO(
        "1xsi",
        "THERY",
        "Jeremie",
        "thery.jeremie@yahoo.fr",
        "Jeremie1",
        new Date(),
        new Adress(),
        "123684IMAGE"
        );
    }
}
