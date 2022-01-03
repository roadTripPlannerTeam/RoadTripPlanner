/*
package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.UnknownStageController;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionRelationDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageUpdateDTO;
import com.mycompany.roadtripplanner.entities.Position;
import com.mycompany.roadtripplanner.entities.Stage;
import com.mycompany.roadtripplanner.services.UnknownStageService;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UnknownStageController.class)
public class UnknownStageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UnknownStageService service;

    @MockBean
    private ModelMapper mapper;

    */
/**
     * Teste la route qui permet de récupérer un tableau de cinémas
     * Vérifie si le code de la requête est de 200, donc que la requête s'est bien passée
     * Vérifie que le retour de cette requête est un tableau vide
     * @throws Exception
     *//*

    @Test
    public void allFindUnknownStagesTest() throws Exception {
        this.mockMvc.perform(get("/unknownstages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    */
/**
     * Test la route qui permet de récupérer un unknownstage
     * Elle doit retourner un statut 400 car je teste avec un Id qui n'existe pas
     * @throws Exception
     *//*

    @Test
    public void testFindNoCreatedUnknownStage() throws Exception{
        this.mockMvc.perform(get("/unknownstages/1"))
                .andExpect(status().isNotFound());
    }

    */
/**
     * Teste la route qui permet de récupérer un unknownstage
     * Doit retourner un unknownstageDTO
     * @throws Exception
     *//*

    @Test
    public void testFindOneUnknownStage() throws Exception{
        // Création d'un unknownstage de test
        UnknownStageDTO unknownstageDTOTest = this.createUnknownStageDTOTest();
        // J'appelle Mockito pour simuler le service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(unknownstageDTOTest));
        // Je teste la route qui permet de récupérer un unknownstage
        MvcResult result = this.mockMvc.perform(get("/unknownstages/5"))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme du format Json récupéré en UnknownStageDTO
        Gson json = new GsonBuilder().create();
        UnknownStageDTO body = json.fromJson(result.getResponse().getContentAsString(), UnknownStageDTO.class);

        // Je test l'égalité des attributs
        Assertions.assertEquals(body.getId(), this.createUnknownStageDTOTest().getId());
        Assertions.assertEquals(body.getStage().getId(), this.createUnknownStageDTOTest().getStage().getId());
    }

    */
/**
     * Teste la route qui permet de créer un unknownstage
     * Vérifie que la requête HTTP est bien 201, car statut CREATED
     * @throws Exception
     *//*

    @Test
    public void testSaveUnknownStage() throws Exception{
        // Création d'un unknownstage de test
        UnknownStageDTO unknownstageDTOTest = this.createUnknownStageDTOTest();
        // Transformation du UnknownStageDTO ver le format Json (String)
        Gson json = new GsonBuilder().create();
        String body = json.toJson(unknownstageDTOTest);
        // Je teste la route qui permet de créer un unknownstage
        this.mockMvc.perform(post("/unknownstages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }


    @Test
    public void testUpdateUnknownStage() throws Exception{
        // Je créé deux unknownstage de test
        UnknownStageDTO unknownstageDTOTest = this.createUnknownStageDTOTest();
        UnknownStageDTO unknownstageDTOUpdate = this.updateUnknownStageDTOTest();

        // J'appelle BDDMockito pour simuler le unknownstage service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(unknownstageDTOTest));
        // Je teste la route qui permet de récupérer un unknownstage
        MvcResult result = this.mockMvc.perform(get("/unknownstages/5"))
                .andExpect(status().isOk())
                .andReturn();

        // Je transforme du format Json récupéré en unknownstageDTO
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        UnknownStageUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(), UnknownStageUpdateDTO.class);
        // Mockito peut prendre un objet de type UnknownStageSaveDTO
        BDDMockito.when(service.update(any(UnknownStageUpdateDTO.class)))
                .thenReturn(unknownstageDTOUpdate);

        // Qu'il va modifier
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/unknownstages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme le format Json en UnknownStageDTO
        String result1 = resultUpdated.getResponse().getContentAsString();
        UnknownStageDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), UnknownStageDTO.class);
        // Je teste les assertions
        Assertions.assertEquals(finalBody.getStage().getId(), this.updateUnknownStageDTOTest().getStage().getId());
    }

    */
/**
     * Teste la route pour supprimer une unknownstage
     * @throws Exception
     *//*

    @Test
    public void testDeleteUnknownStage() throws Exception{
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        UnknownStageDTO unknownstageDTO = this.createUnknownStageDTOTest();
        String body = json.toJson(unknownstageDTO);
        mockMvc.perform(delete("/unknownstages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    */
/**
     * Créé un unknownstageDTO utilisé pour le test save
     * @return UnknownStageDTO
     *//*

    private UnknownStageDTO createUnknownStageDTOTest(){
        return new UnknownStageDTO("5", new Stage("3", "stagetest", new Position()), new ArrayList<>());
    }

    */
/**
     * Créé un unknownstageUpdateDTO utilisé pour l'update
     * @return UnknownStageUpdateDTO
     *//*

    private UnknownStageDTO updateUnknownStageDTOTest(){
        return new UnknownStageDTO ("5", new Stage("5", "stagetest", new Position()), new ArrayList<>());
    }
}*/
