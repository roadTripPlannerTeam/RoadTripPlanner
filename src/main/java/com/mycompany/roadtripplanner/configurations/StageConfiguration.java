package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import com.mycompany.roadtripplanner.services.StageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StageConfiguration {
    @Bean
    public StageService stageService(ModelMapper mapper, StageRepositoryImpl repository) {
        return new StageService(mapper, repository);
    }
}
