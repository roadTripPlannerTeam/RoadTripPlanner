package com.mycompany.roadtripplanner.configurations;

import com.mycompany.roadtripplanner.repositories.TodoListRepositoryImpl;
import com.mycompany.roadtripplanner.services.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoListConfiguration {

    @Bean
    public TodoListService todoListService(TodoListRepositoryImpl repository, ModelMapper mapper){
            return new TodoListService(mapper, repository);
    }
}
