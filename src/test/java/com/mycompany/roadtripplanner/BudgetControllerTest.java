package com.mycompany.roadtripplanner;


import com.mycompany.roadtripplanner.controllers.BudgetController;
import com.mycompany.roadtripplanner.services.BudgetService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


}