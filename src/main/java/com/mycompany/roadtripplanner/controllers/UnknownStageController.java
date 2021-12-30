package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageDeleteDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageSaveDTO;
import com.mycompany.roadtripplanner.dtos.unknownstage.UnknownStageUpdateDTO;
import com.mycompany.roadtripplanner.services.UnknownStageService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/unknownstages")
public class UnknownStageController {

    UnknownStageService service;
    ModelMapper mapper;

    /**
     * Constructeur de UnknownStageController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public UnknownStageController(UnknownStageService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir tous les unknownStages
     * @return liste de unknownStage
     */
    @GetMapping
    public List<UnknownStageDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir un unknownStage selon son Id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("{id}")
    public ResponseEntity<UnknownStageDTO> find(@PathVariable String id){
        try{
            return ResponseEntity.ok(service.find(id).get());
        } catch(NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Créé la route pour créer un objet unknownStage
     * @param unknownStageSaveDTO
     * @return un statut Http 201 Created
     */
    @PostMapping
    public ResponseEntity<UnknownStageDTO> save(@RequestBody UnknownStageSaveDTO unknownStageSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unknownStageSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet unknownStage
     * @param unknownStageUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<UnknownStageDTO> update(@RequestBody UnknownStageUpdateDTO unknownStageUpdateDTO){
        return ResponseEntity.ok(service.update(unknownStageUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet unknownStage via son objet
     * @param unknownStageDeleteDTO
     * @return un statut Http 200 ok true
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody UnknownStageDeleteDTO unknownStageDeleteDTO){
        service.delete(unknownStageDeleteDTO);
        return ResponseEntity.ok("unknownStage bien supprimé ");
    }
    
}