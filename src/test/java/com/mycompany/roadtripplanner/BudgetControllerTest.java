package com.mycompany.roadtripplanner;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.roadtripplanner.controllers.BudgetController;
import com.mycompany.roadtripplanner.dtos.budget.BudgetDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetSaveDTO;
import com.mycompany.roadtripplanner.dtos.budget.BudgetUpdateDTO;
import com.mycompany.roadtripplanner.repositories.BudgetRepositoryImpl;
import com.mycompany.roadtripplanner.services.BudgetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BudgetController.class)
public class BudgetControllerTest {

    @Autowired
    private  MockMvc mockMvc;
    @MockBean
    private BudgetService service;


    @MockBean
    private ModelMapper mapper;

    /**
     * Teste la route qui permet de récupérer un tableau de cinémas
     * Vérifie si le code de la requête est de 200, donc que la requête s'est bien passée
     * Vérifie que le retour de cette requête est un tableau vide
     * @throws Exception
     */
    @Test
    public void allFindBudgetsTest() throws Exception {
        this.mockMvc.perform(get("/budgets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    /**
     * Test la route qui permet de récupérer un budget
     * Elle doit retourner un statut 400 car je teste avec un Id qui n'existe pas
     * @throws Exception
     */
    @Test
    public void testFindNoCreatedBudget() throws Exception{
        this.mockMvc.perform(get("/budgets/1"))
                .andExpect(status().isNotFound());
    }

    /**
     * Teste la route qui permet de récupérer un budget
     * Doit retourner un budgetDTO
     * @throws Exception
     */
    @Test
    public void testFindOneBudget() throws Exception{
        // Création d'un budget de test
        BudgetDTO budgetDTOTest = this.createBudgetDTOTest();
        // J'appelle Mockito pour simuler le service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(budgetDTOTest));
        // Je teste la route qui permet de récupérer un budget
        MvcResult result = this.mockMvc.perform(get("/budgets/5"))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme du format Json récupéré en BudgetDTO
        Gson json = new GsonBuilder().create();
        BudgetDTO body = json.fromJson(result.getResponse().getContentAsString(), BudgetDTO.class);

        // Je test l'égalité des attributs
        Assertions.assertEquals(body.getId(), this.createBudgetDTOTest().getId());
        Assertions.assertEquals(body.getExpense(), this.createBudgetDTOTest().getExpense());
    }

    /**
     * Teste la route qui permet de créer un budget
     * Vérifie que la requête HTTP est bien 201, car statut CREATED
     * @throws Exception
     */
    @Test
    public void testSaveBudget() throws Exception{
        // Création d'un budget de test
        BudgetDTO budgetDTOTest = this.createBudgetDTOTest();
        // Transformation du BudgetDTO ver le format Json (String)
        Gson json = new GsonBuilder().create();
        String body = json.toJson(budgetDTOTest);
        // Je teste la route qui permet de créer un budget
        this.mockMvc.perform(post("/budgets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());
    }


    @Test
    public void testUpdateBudget() throws Exception{
        // Je créé deux budget de test
        BudgetDTO budgetDTOTest = this.createBudgetDTOTest();
        BudgetDTO budgetDTOUpdate = this.updateBudgetDTOTest();

        // J'appelle BDDMockito pour simuler le budget service
        BDDMockito.given(service.find("5"))
                .willReturn(Optional.of(budgetDTOTest));
        // Je teste la route qui permet de récupérer un budget
        MvcResult result = this.mockMvc.perform(get("/budgets/5"))
                                        .andExpect(status().isOk())
                                        .andReturn();

        // Je transforme du format Json récupéré en budgetDTO
        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        BudgetUpdateDTO body = json.fromJson(result.getResponse().getContentAsString(), BudgetUpdateDTO.class);
        // Mockito peut prendre un objet de type BudgetSaveDTO
        BDDMockito.when(service.update(any(BudgetUpdateDTO.class)))
                .thenReturn(budgetDTOUpdate);

        // Qu'il va modifier
        String bodyToSave = json.toJson(body);
        MvcResult resultUpdated = this.mockMvc.perform(put("/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bodyToSave))
                .andExpect(status().isOk())
                .andReturn();
        // Je transforme le format Json en BudgetDTO
        String result1 = resultUpdated.getResponse().getContentAsString();
        BudgetDTO finalBody = json.fromJson(resultUpdated.getResponse().getContentAsString(), BudgetDTO.class);
        // Je teste les assertions
        Assertions.assertEquals(finalBody.getExpense(), this.updateBudgetDTOTest().getExpense());
    }

    /**
     * Créé un budgetDTO utilisé pour le test save
     * @return BudgetDTO
     */
    private BudgetDTO createBudgetDTOTest(){
        return new BudgetDTO("5", 5000F, 100F);
    }

    /**
     * Créé un budgetUpdateUpdateDTO utilisé pour l'update
     * @return BudgetUpdateDTO
     */
    private BudgetDTO updateBudgetDTOTest(){
        return new BudgetDTO ("5", 3000F, 50F);
    }

    @Test
    public void testDeleteBudget() throws Exception{

        Gson json = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();

        BudgetDTO budgetDTO = this.createBudgetDTOTest();

        String body = json.toJson(budgetDTO);
                 mockMvc.perform(delete("/budgets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        //
    }

}