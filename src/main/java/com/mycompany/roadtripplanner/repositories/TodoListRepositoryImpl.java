package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoListRepositoryImpl extends MongoRepository<TodoList, String> {
}
