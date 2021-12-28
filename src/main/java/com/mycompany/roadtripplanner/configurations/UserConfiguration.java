package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.UserRepositoryImpl;
import com.mycompany.roadtripplanner.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(ModelMapper mapper, UserRepositoryImpl repository, CommentRepositoryImpl commentRepository, ItineraryRepositoryImpl itineraryRepository){
        return new UserService(mapper, repository,commentRepository,itineraryRepository);
    }

}
