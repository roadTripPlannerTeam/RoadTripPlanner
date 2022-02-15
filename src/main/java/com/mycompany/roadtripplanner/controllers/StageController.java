package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.stage.*;
import com.mycompany.roadtripplanner.services.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/stages")
public class StageController {

    StageService service;



    public StageController(StageService service) {
        this.service = service;
    }

    /**
     * Accès à toutes les étapes 'stage'
     *
     * @return List<StageGetDTO>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StageGetDTO> findAll() {
        return service.findAll();
    }

    /**
     * Accès à une étape via son id
     *
     * @param id
     * @return ResponseEntity<StageGetDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<StageGetDTO> findById(@PathVariable String id) {
        try{
            Optional<StageGetDTO> stageDTO = service.findById(id);
            return (ResponseEntity.ok(stageDTO.get()));

        }catch (NoSuchElementException e ){
            return (ResponseEntity.notFound().header(e.getMessage()).build());
        }
    }

    /**
     * Création et sauvegarde d'une étape 'stage'
     *
     * @param stageSaveDTO
     * @return StageGetDTO
     */
    @PostMapping
    public ResponseEntity<StageGetSaveDTO> create(@RequestBody StageSaveDTO stageSaveDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(stageSaveDTO));
    }

    /**
     * Modification d'une étape 'stage'
     *
     * @param stage
     * @return ResponseEntity<StageGetDTO>
     */
    @PutMapping
    public ResponseEntity<StageGetDTO> update(@RequestBody StageUpdateDTO stage) {
        return ResponseEntity.ok(service.update(stage));
    }

    /**
     *
     * @param stage
     * @return String message
     */
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody StageDeleteDTO stage) {
        service.delete(stage);
        return ResponseEntity.ok("stage bien supprimé");
    }

}
