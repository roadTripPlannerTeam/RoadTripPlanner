package com.mycompany.roadtripplanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.PositionController;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionUpdateDTO;
import com.mycompany.roadtripplanner.services.PositionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PositionController.class)
public class PositionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PositionService service;

    /**
     * teste la route qui permet de récupérer toutes les positions
     * @throws Exception
     */
    @Test
    public void testFindAllPositions() throws Exception {
        this.mockMvc.perform(get("/positions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Teste la route d'une position via son id
     * @throws Exception
     */
    @Test
    public void testFindOnePosition() throws Exception {
        PositionGetDTO positionGetDTO = this.positionGetDTO();
        BDDMockito.given(service.findById("1"))
                .willReturn(Optional.of(positionGetDTO));
        MvcResult result = this.mockMvc.perform(get("/positions/1"))
                .andExpect(status().isOk())
                .andReturn();
        Gson json = new GsonBuilder().create();
        PositionGetDTO body = json.fromJson(
                result.getResponse().getContentAsString(),
                PositionGetDTO.class
        );
        Assertions.assertEquals(body.getId(), this.positionGetDTO().getId());
        Assertions.assertEquals(body.getLongitude(), this.positionGetDTO().getLongitude());
        Assertions.assertEquals(body.getLatitude(), this.positionGetDTO().getLatitude());
    }

    /**
     * teste si une position est inexistante
     * @throws Exception
     */
    @Test
    public void testFindOnePositionInexistant() throws Exception {
        this.mockMvc.perform(get("/positions/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * teste la route qui gère la création et la sauvegarde d'une position
     * @throws Exception
     */
    @Test
    public void testSavePosition() throws Exception {
        PositionGetDTO positionGetDTO = this.positionGetDTO();
        Gson json = new GsonBuilder().create();
        String body = json.toJson(positionGetDTO);
        this.mockMvc.perform(post("/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
    }

    /**
     * teste la modification d'une position
     * @throws Exception
     */
    @Test
    public void testUpdatePosition() throws Exception {
        // récup une position
        PositionGetDTO positionGetDTO = this.positionGetDTO();
        PositionGetDTO positionGetUpdateDTO = this.positionGetUpdateDTO();
        BDDMockito.given(service.findById("1"))
                .willReturn(Optional.of(positionGetDTO));
        MvcResult result = this.mockMvc.perform(get("/positions/1"))
                .andExpect(status().isOk())
                .andReturn();
        Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        PositionGetDTO body = json.fromJson(result.getResponse().getContentAsString(), PositionGetDTO.class);
        BDDMockito.when(service.update(any(PositionUpdateDTO.class)))
                .thenReturn(positionGetUpdateDTO);
        // modif
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // transformer le result en objet
        PositionGetDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), PositionGetDTO.class);
        // vérif les modif
        Assertions.assertEquals(finalBody.getLongitude(), this.positionGetUpdateDTO().getLongitude());
        Assertions.assertEquals(finalBody.getLatitude(), this.positionGetUpdateDTO().getLatitude());
    }

    /**
     * teste la suppression d'une position
     * @throws Exception
     */
    @Test
    public void testDeletePosition() throws Exception {
        Gson json = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String body = json.toJson(this.positionGetDTO());
        this.mockMvc.perform(delete("/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }

    private PositionGetDTO positionGetDTO() {
        return new PositionGetDTO(
                "1",
                54.52563,
                12.26899
        );
    }

    private PositionGetDTO positionGetUpdateDTO() {
        return new PositionGetDTO(
                "1",
                52.36999,
                42.54569
        );
    }
}
