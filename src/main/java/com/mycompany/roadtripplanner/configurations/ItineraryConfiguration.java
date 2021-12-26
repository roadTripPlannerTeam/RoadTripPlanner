package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import com.mycompany.roadtripplanner.services.ItineraryService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItineraryConfiguration {

    @Bean
    public ItineraryService commentService(ItineraryRepositoryImpl repository, ModelMapper mapper, CommentRepositoryImpl commentRepository){
        return new ItineraryService(mapper, repository,commentRepository);
    }

}
