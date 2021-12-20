package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.ConversionRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.TodoListRepositoryImpl;
import com.mycompany.roadtripplanner.services.ConversionService;
import com.mycompany.roadtripplanner.services.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfiguration {

        @Bean
        public ConversionService conversionService(ConversionRepositoryImpl repository, ModelMapper mapper){
            return new ConversionService(mapper, repository);
        }
}
