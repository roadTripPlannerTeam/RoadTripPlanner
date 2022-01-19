package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.InterestPointRepositoryImpl;
import com.mycompany.roadtripplanner.services.InterestPointService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterestPointConfiguration {

    @Bean
    public InterestPointService interestPointService(InterestPointRepositoryImpl repository, ModelMapper mapper){
        return new InterestPointService(mapper, repository);
    }

}
