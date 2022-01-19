//package com.mycompany.roadtripplanner;
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.mycompany.roadtripplanner.controllers.InterestPointController;
//import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointDTO;
//import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointUpdateDTO;
//import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
//import com.mycompany.roadtripplanner.services.InterestPointService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import static org.mockito.ArgumentMatchers.any;
//
//import java.util.Optional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(controllers = InterestPointController.class)
//public class InterestPointControllerTest {
//
//    @Autowired
//    private  MockMvc mockMvc;
//    @MockBean
//    private InterestPointService service;
//
//
//    @MockBean
//    private ModelMapper mapper;
//
//    /**
//     * Teste la route qui permet de récupérer un tableau de cinémas
//     * Vérifie si le code de la requête est de 200, donc que la requête s'est bien passée
//     * Vérifie que le retour de cette requête est un tableau vide
//     * @throws Exception
//     */
//    @Test
//    public void allFindInterestPointsTest() throws Exception {
//        this.mockMvc.perform(get("/interestpoints"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isEmpty());
//    }
//
//    /**
//     * Test la route qui permet de récupérer un interestPoint
//     * Elle doit retourner un statut 400 car je teste avec un Id qui n'existe pas
//     * @throws Exception
//     */
//    @Test
//    public void testFindNoCreatedInterestPoint() throws Exception{
//        this.mockMvc.perform(get("/interestpoints/1"))
//                .andExpect(status().isNotFound());
//    }
//
//    /**
//     * Teste la route qui permet de récupérer un interestPoint
//     * Doit retourner un interestPointDTO
//     * @throws Exception
//     */
//    @Test
//    public void testFindOneInterestPoint() throws Exception{
//        // Création d'un interestPoint de test
//        InterestPointDTO interestPointDTOTest = this.createInterestPointDTOTest();
//        // J'appelle Mockito pour simuler le service
//        BDDMockito.given(service.find("5"))
//                .willReturn(Optional.of(interestPointDTOTest));
//        // Je teste la route qui permet de récupérer un interestPoint
//        MvcResult result = this.mockMvc.perform(get("/interestpoints/5"))
//                .andExpect(status().isOk())
//                .andReturn();
//        // Je transforme du format Json récupéré en InterestPointDTO
//        Gson json = new GsonBuilder().create();
//        InterestPointDTO body = json.fromJson(result.getResponse().getContentAsString(), InterestPointDTO.class);
//
//        // Je test l'égalité des attributs
//        Assertions.assertEquals(body.getId(), this.createInterestPointDTOTest().getId());
//        Assertions.assertEquals(body.getName(), this.createInterestPointDTOTest().getName());
//    }
//
//    /**
//     * Teste la route qui permet de créer un interestPoint
//     * Vérifie que la requête HTTP est bien 201, car statut CREATED
//     * @throws Exception
//     */
//    @Test
//    public void testSaveInterestPoint() throws Exception{
//        // Création d'un interestPoint de test
//        InterestPointDTO interestPointDTOTest = this.createInterestPointDTOTest();
//        // Transformation du InterestPointDTO ver le format Json (String)
//        Gson json = new GsonBuilder().create();
//        String body = json.toJson(interestPointDTOTest);
//        // Je teste la route qui permet de créer un interestPoint
//        this.mockMvc.perform(post("/interestpoints")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isCreated());
//    }
//
//
//    @Test
//    public void testUpdateInterestPoint() throws Exception{
//        // Je créé deux interestPoint de test
//        InterestPointDTO interestPointDTOTest = this.createInterestPointDTOTest();
//        InterestPointDTO interestPointDTOUpdate = this.updateInterestPointDTOTest();
//
//        // J'appelle BDDMockito pour simuler le interestPoint service
//        BDDMockito.given(service.find("5"))
//                .willReturn(Optional.of(interestPointDTOTest));
//        // Je teste la route qui permet de récupérer un interestPoint
//        MvcResult result = this.mockMvc.perform(get("/interestpoints/5"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Je transforme du format Json récupéré en interestPointDTO
//        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
//        InterestPointUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(), InterestPointUpdateDTO.class);
//        // Mockito peut prendre un objet de type InterestPointSaveDTO
//        BDDMockito.when(service.update(any(InterestPointUpdateDTO.class)))
//                .thenReturn(interestPointDTOUpdate);
//
//        // Qu'il va modifier
//        String bodyToSave = json.toJson(body);
//        MvcResult resultUpdated = this.mockMvc.perform(put("/interestpoints")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(bodyToSave))
//                .andExpect(status().isOk())
//                .andReturn();
//        // Je transforme le format Json en InterestPointDTO
//        String result1 = resultUpdated.getResponse().getContentAsString();
//        InterestPointDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), InterestPointDTO.class);
//        // Je teste les assertions
//        Assertions.assertEquals(finalBody.getName(), this.updateInterestPointDTOTest().getName());
//    }
//
//    /**
//     * Teste la route pour supprimer un InterestPoint
//     * @throws Exception
//     */
//    @Test
//    public void testDeleteInterestPoint() throws Exception{
//        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
//        InterestPointDTO interestPointDTO = this.createInterestPointDTOTest();
//        String body = json.toJson(interestPointDTO);
//        mockMvc.perform(delete("/interestpoints")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body))
//                .andExpect(status().isOk());
//    }
//
//    /**
//     * Créé un interestPointDTO utilisé pour le test save
//     * @return InterestPointDTO
//     */
//    private InterestPointDTO createInterestPointDTOTest(){
//        return new InterestPointDTO("5", "banque test", new PositionGetDTO(), "banque", "10 rue osef");
//    }
//
//    /**
//     * Créé un interestPointUpdateUpdateDTO utilisé pour l'update
//     * @return InterestPointUpdateDTO
//     */
//    private InterestPointDTO updateInterestPointDTOTest(){
//        return new InterestPointDTO ("5", "hotel test", new PositionGetDTO(), "hotel", "15 rue osef");
//    }
//}