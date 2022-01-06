package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.InterestPointRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.UnknownStageRepositoryImpl;
import com.mycompany.roadtripplanner.services.UnknownStageService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnknownStageConfiguration {

    @Bean
    public UnknownStageService unknownStageService(UnknownStageRepositoryImpl repository, ModelMapper mapper, InterestPointRepositoryImpl interestPointRepository){
        return new UnknownStageService(mapper, repository, interestPointRepository);
    }

}
