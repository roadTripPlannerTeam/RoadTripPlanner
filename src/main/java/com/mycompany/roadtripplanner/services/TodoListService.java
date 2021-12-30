package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDeleteDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListSaveDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListUpdateDTO;
import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.entities.TodoList;
import com.mycompany.roadtripplanner.repositories.TodoListRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TodoListService {

    TodoListRepositoryImpl repository;
    ModelMapper mapper;

    public TodoListService(ModelMapper mapper, TodoListRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Constructeur de TodoListService
     * Injection de dépendance
     * @param repository
     * @param mapper
     */
    public TodoListService(TodoListRepositoryImpl repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Va retourner une liste de tous les todolists
     * @return liste de totodolists
     */
    public List<TodoListDTO> findAll(){
        List<TodoListDTO> todoListDTOS = new ArrayList<>();
        this.repository.findAll().forEach(todoList -> {
            todoListDTOS.add(mapper.map(todoList, TodoListDTO.class));
        });
        return todoListDTOS;
    }

    /**
     * Va retourner une todolist par son id
     * @param id
     * @return un objet todolist
     */
    public Optional<TodoListDTO> find(String id) throws NoSuchElementException {
        Optional<TodoList> todoListOptional = repository.findById(id);
        Optional<TodoListDTO> todoListDTO = null;
        if(todoListOptional.isPresent()){
            todoListDTO = Optional.of(mapper.map(todoListOptional.get(), TodoListDTO.class));
        } else {
            throw new NoSuchElementException("Cette todolist n'existe pas");
        }
        return todoListDTO;
    }

    /**
     * Va créer un nouvel objet todolist
     * @param obj
     * @return l'objet todolist créé
     */
    public TodoListDTO save(TodoListSaveDTO obj){
        TodoList todoListToSave = mapper.map(obj, TodoList.class);
        TodoList todoList = repository.save(todoListToSave);
        TodoListDTO todoListSaved = mapper.map(todoList, TodoListDTO.class);
        return todoListSaved;
    }

    /**
     * Va modifier un objet todolist
     * @param obj
     * @return l'objet todolist modifié
     */
    public TodoListDTO update(TodoListUpdateDTO obj){
        TodoList todoListToUpdate = mapper.map(obj, TodoList.class);
        TodoList todoList = repository.save(todoListToUpdate);
        TodoListDTO todoListSaved = mapper.map(todoList, TodoListDTO.class);
        return todoListSaved;
    }

    /**
     * Va supprimer un objet conversion grâce à son id
     * @param todoListDeleteDTO
     */
    public void delete(TodoListDeleteDTO todoListDeleteDTO){
        repository.delete( mapper.map(todoListDeleteDTO, TodoList.class));
    }

}
