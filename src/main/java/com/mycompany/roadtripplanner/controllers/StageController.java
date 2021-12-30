package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.stage.StageDeleteDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageGetDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageSaveDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageUpdateDTO;
import com.mycompany.roadtripplanner.services.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
     * @param stage
     * @return ResponseEntity<StageDTO>
     */
    @PostMapping
    public ResponseEntity<StageGetDTO> create(@RequestBody StageSaveDTO stage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(stage));
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
        return ResponseEntity.ok("stage bien supprimée");
    }

}
