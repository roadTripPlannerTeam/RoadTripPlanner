package com.mycompany.roadtripplanner;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.TodoListController;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListUpdateDTO;
import com.mycompany.roadtripplanner.services.TodoListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoListController.class)
public class TodoListControllerTest {

    @Autowired
    private  MockMvc mockMvc;
    @MockBean
    private TodoListService service;


    @MockBean
    private ModelMapper mapper;

    /**
     * Teste la route qui permet de récupérer un tableau de cinémas
     * Vérifie si le code de la requête est de 200, donc que la requête s'est bien passée
     * Vérifie que le retour de cette requête est un tableau vide
     * @throws Exception
     */
    @Test
    public void allFindTodoListsTest() throws Exception {
        this.mockMvc.perform(get("/todolists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Test la route qui permet de récupérer un todoList
     * Elle doit retourner un statut 400 car je teste avec un Id qui n'existe pas
     * @throws Exception
     */
    @Test
    public void testFindNoCreatedTodoList() throws Exception{
        this.mockMvc.perform(get("/todolists/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * Teste la route qui permet de récupérer un todoList
     * Doit retourner un todoListDTO
     * @throws Exception
     */
    @Test
    public void testFindOneTodoList() throws Exception{
        // Création d'un todoList de test
        TodoListDTO todoListDTOTest = this.createTodoListDTOTest();
        // J'appelle Mockito pour simuler le service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(todoListDTOTest));
        // Je teste la route qui permet de récupérer un todoList
        MvcResult result = this.mockMvc.perform(get("/todolists/5"))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme du format Json récupéré en TodoListDTO
        Gson json = new GsonBuilder().create();
        TodoListDTO body = json.fromJson(result.getResponse().getContentAsString(), TodoListDTO.class);

        // Je test l'égalité des attributs
        Assertions.assertEquals(body.getId(), this.createTodoListDTOTest().getId());
        Assertions.assertEquals(body.getTitle(), this.createTodoListDTOTest().getTitle());
    }

    /**
     * Teste la route qui permet de créer un todoList
     * Vérifie que la requête HTTP est bien 201, car statut CREATED
     * @throws Exception
     */
    @Test
    public void testSaveTodoList() throws Exception{
        // Création d'un todoList de test
        TodoListDTO todoListDTOTest = this.createTodoListDTOTest();
        // Transformation du TodoListDTO ver le format Json (String)
        Gson json = new GsonBuilder().create();
        String body = json.toJson(todoListDTOTest);
        // Je teste la route qui permet de créer un todoList
        this.mockMvc.perform(post("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }


    @Test
    public void testUpdateTodoList() throws Exception{
        // Je créé deux todoList de test
        TodoListDTO todoListDTOTest = this.createTodoListDTOTest();
        TodoListDTO todoListDTOUpdate = this.updateTodoListDTOTest();

        // J'appelle BDDMockito pour simuler le todoList service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(todoListDTOTest));
        // Je teste la route qui permet de récupérer un todoList
        MvcResult result = this.mockMvc.perform(get("/todolists/5"))
                .andExpect(status().isOk())
                .andReturn();

        // Je transforme du format Json récupéré en todoListDTO
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        TodoListUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(), TodoListUpdateDTO.class);
        // Mockito peut prendre un objet de type TodoListSaveDTO
        BDDMockito.when(service.update(any(TodoListUpdateDTO.class)))
                .thenReturn(todoListDTOUpdate);

        // Qu'il va modifier
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme le format Json en TodoListDTO
        String result1 = resultUpdated.getResponse().getContentAsString();
        TodoListDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), TodoListDTO.class);
        // Je teste les assertions
        Assertions.assertEquals(finalBody.getTitle(), this.updateTodoListDTOTest().getTitle());
    }

    /**
     * Teste la route pour supprimer une todolist
     * @throws Exception
     */
    @Test
    public void testDeleteTodoList() throws Exception{
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        TodoListDTO todoListDTO = this.createTodoListDTOTest();
        String body = json.toJson(todoListDTO);
        mockMvc.perform(delete("/todolists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    /**
     * Créé un todoListDTO utilisé pour le test save
     * @return TodoListDTO
     */
    private TodoListDTO createTodoListDTOTest(){
        return new TodoListDTO("5", "ma todo liste", "acheter des souvenirs");
    }

    /**
     * Créé un todoListUpdateDTO utilisé pour l'update
     * @return TodoListUpdateDTO
     */
    private TodoListDTO updateTodoListDTOTest(){
        return new TodoListDTO ("5", "ma nouvelle todo liste", "acheter d'autres souvenirs");
    }
}