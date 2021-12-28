package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.StageController;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageGetDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageUpdateDTO;
import com.mycompany.roadtripplanner.services.StageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
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

@WebMvcTest(controllers = StageController.class)
public class StageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StageService service;

    /**
     * Teste la route "/stages" qui permet de récupérer
     * toutes les étapes 'stages'
     * @throws Exception
     */
    @Test
    public void testFindAllStages() throws Exception {
        this.mockMvc.perform(get("/stages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Teste la route d'une étape via son id
     * @throws Exception
     */
    @Test
    public void testFindOneStage() throws Exception {
        StageGetDTO stageGetDTO = this.stageGetDTO();
        BDDMockito.given(service.findById("1"))
                .willReturn(Optional.of(stageGetDTO));
        MvcResult result = this.mockMvc.perform(get("/stages/1"))
                .andExpect(status().isOk())
                .andReturn();
        Gson json = new GsonBuilder().create();
        StageGetDTO body = json.fromJson(
                result.getResponse().getContentAsString(),
                StageGetDTO.class
        );
        Assertions.assertEquals(body.getId(), this.stageGetDTO().getId());
        Assertions.assertEquals(body.getName(), this.stageGetDTO().getName());
        Assertions.assertEquals(body.getPosition(), this.stageGetDTO().getPosition());
    }

    /**
     * teste si une étape est inexistante
     * @throws Exception
     */
    @Test
    public void testFindOneStageInexistant() throws Exception {
        this.mockMvc.perform(get("/stages/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * teste la route qui gère la création et la sauvegarde d'une étape
     * @throws Exception
     */
    @Test
    public void testSaveStage() throws Exception {
        StageGetDTO stageGetDTO = this.stageGetDTO();
        Gson json = new GsonBuilder().create();
        String body = json.toJson(stageGetDTO);
        this.mockMvc.perform(post("/stages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());
    }

    /**
     * teste la modification d'une étape
     * @throws Exception
     */
    @Test
    public void testUpdateStage() throws Exception {
        // récup une étape
        StageGetDTO stageGetDTO = this.stageGetDTO();
        StageGetDTO stageGetUpdateDTO = this.stageGetUpdateDTO();
        BDDMockito.given(service.findById("1"))
                .willReturn(Optional.of(stageGetDTO));
        MvcResult result = this.mockMvc.perform(get("/stages/1"))
                .andExpect(status().isOk())
                .andReturn();
        Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        StageGetDTO body = json.fromJson(result.getResponse().getContentAsString(), StageGetDTO.class);
        BDDMockito.when(service.update(any(StageUpdateDTO.class)))
                .thenReturn(stageGetUpdateDTO);
        // modif
        body.setName("stage2");
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/stages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // transformer le result en objet
        StageGetDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), StageGetDTO.class);
        // vérif les modif
        Assertions.assertEquals(finalBody.getName(), this.stageGetUpdateDTO().getName());
    }

    /**
     * teste la suppression d'une étape
     * @throws Exception
     */
    @Test
    public void testDeleteStage() throws Exception {
        Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String body = json.toJson(this.stageGetDTO());
        this.mockMvc.perform(delete("/stages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk());
    }

    /**
     * méthode qui crée une étape pour les tests
     * @return
     */
    private StageGetDTO stageGetDTO() {
        return new StageGetDTO(
                "1",
                "stage1",
                new PositionGetDTO()
        );
    }

    private StageGetDTO stageGetUpdateDTO() {
        return new StageGetDTO(
                "1",
                "stage2",
                new PositionGetDTO()
        );
    }
}
