package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListSaveDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListUpdateDTO;
import com.mycompany.roadtripplanner.entities.TodoList;
import com.mycompany.roadtripplanner.repositories.TodoListRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {

    TodoListRepositoryImpl repository;
    ModelMapper mapper;

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
    public TodoListDTO find(String id){
        Optional<TodoList> t = repository.findById(id);
        TodoListDTO todoListDTO = null;
        if(t.isPresent()){
            todoListDTO = mapper.map(t.get(), TodoListDTO.class);
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
     * @param id
     */
    public void delete(String id){
        repository.deleteById(id);
    }

}
