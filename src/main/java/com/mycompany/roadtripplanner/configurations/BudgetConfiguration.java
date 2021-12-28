package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.BudgetRepositoryImpl;
import com.mycompany.roadtripplanner.services.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BudgetConfiguration {

    @Bean
    public BudgetService budgetService(BudgetRepositoryImpl repository, ModelMapper mapper){
        return new BudgetService(mapper, repository);
    }

}
