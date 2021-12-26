package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.PositionRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import com.mycompany.roadtripplanner.services.PositionService;
import com.mycompany.roadtripplanner.services.StageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PositionConfiguration {
    @Bean
    public PositionService positionService(
            ModelMapper mapper,
            PositionRepositoryImpl repository
    ) {
        return new PositionService(repository, mapper);
    }
}
