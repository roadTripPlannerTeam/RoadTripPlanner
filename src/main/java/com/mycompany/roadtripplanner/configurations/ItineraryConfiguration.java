package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.entities.Stage;
import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import com.mycompany.roadtripplanner.services.ItineraryService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItineraryConfiguration {

    @Bean
    public ItineraryService itineraryService(ItineraryRepositoryImpl repository, ModelMapper mapper, CommentRepositoryImpl commentRepository, StageRepositoryImpl stageRepository){
        return new ItineraryService(mapper, repository,commentRepository,stageRepository);
    }

}
