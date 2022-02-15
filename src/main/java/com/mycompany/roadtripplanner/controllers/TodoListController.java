package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.todolist.TodoListDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListDeleteDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListSaveDTO;
import com.mycompany.roadtripplanner.dtos.todolist.TodoListUpdateDTO;
import com.mycompany.roadtripplanner.services.TodoListService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/todolists")
public class TodoListController {

    TodoListService service;
    ModelMapper mapper;

    /**
     * Constructeur de TodoListController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public TodoListController(TodoListService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir tous les todolists
     * @return list de todolists
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TodoListDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir une totolist selon son id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("{id}")
    public ResponseEntity<TodoListDTO> find(@PathVariable String id){
        try{
            return ResponseEntity.ok(service.find(id).get());
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Créé la route pour créer un objet todolist
     * @param todoListSaveDTO
     * @return un statut Http 201 created
     */
    @PostMapping
    public ResponseEntity<TodoListDTO> save(@RequestBody TodoListSaveDTO todoListSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(todoListSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet budget
     * @param todoListUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<TodoListDTO> update(@RequestBody TodoListUpdateDTO todoListUpdateDTO){
        return ResponseEntity.ok(service.update(todoListUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet todolist via son Id
     * @return un statut Http 200 ok true
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody TodoListDeleteDTO todoListDeleteDTO){
        service.delete(todoListDeleteDTO);
        return ResponseEntity.ok("todolist bien supprimé");
    }
}