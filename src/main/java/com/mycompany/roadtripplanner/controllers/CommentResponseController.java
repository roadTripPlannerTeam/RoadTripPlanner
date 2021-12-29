package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.commentResponse.*;
import com.mycompany.roadtripplanner.services.CommentResponseService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/commentResponses")
public class CommentResponseController {

    CommentResponseService service;
    ModelMapper mapper;

    /**
     * Constructeur de CommentResponseController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public CommentResponseController(CommentResponseService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir tous les commentResponses
     * @return liste de commentResponse
     */
    @GetMapping
    public List<CommentResponseDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir un commentResponse selon son Id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("{id}")
    public ResponseEntity<CommentResponseDTO> find(@PathVariable String id){
        try{
            return ResponseEntity.ok(service.find(id).get());
        } catch(NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Créé la route pour créer un objet commentResponse
     * @param commentResponseSaveDTO
     * @return un statut Http 201 Created
     */
    @PostMapping
    public ResponseEntity<CommentResponseGetSaveDTO> save(@RequestBody CommentResponseSaveDTO commentResponseSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(commentResponseSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet commentResponse
     * @param commentResponseUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<CommentResponseDTO> update(@RequestBody CommentResponseUpdateDTO commentResponseUpdateDTO){
        return ResponseEntity.ok(service.update(commentResponseUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet commentResponse via son Id
     * @param  commentResponseDeleteDTO CommentResponseDeleteDTO
     * @return un statut Http 200 ok true
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody CommentResponseDeleteDTO commentResponseDeleteDTO ){
        service.delete(commentResponseDeleteDTO);
        return ResponseEntity.ok("commentResponse bien supprimé ");
    }
}