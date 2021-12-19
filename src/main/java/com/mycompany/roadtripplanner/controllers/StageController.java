package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.stage.StageDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageDeleteDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageSaveDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageUpdateDTO;
import com.mycompany.roadtripplanner.services.StageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {

    StageService service;

    public StageController(StageService service) {
        this.service = service;
    }

    /**
     * Accès à toutes les étapes 'stage'
     * @return List<StageDTO>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StageDTO> findAll() {
        return service.findAll();
    }

    /**
     * Accès à une étape via son id
     * @param id
     * @return ResponseEntity<StageDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<StageDTO> find(@PathVariable String id) {
        StageDTO stageDTO = service.find(id);
        if (stageDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stageDTO);
    }

    /**
     * Création et sauvegarde d'une étape 'stage'
     * @param stage
     * @return ResponseEntity<StageDTO>
     */
    @PostMapping
    public ResponseEntity<StageDTO> create(@RequestBody StageSaveDTO stage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(stage));
    }

    /**
     * Modification d'une étape 'stage'
     * @param stage
     * @return ResponseEntity<StageDTO>
     */
    @PutMapping
    public ResponseEntity<StageDTO> update(@RequestBody StageUpdateDTO stage) {
        return ResponseEntity.ok(service.update(stage));
    }

    /**
     * Supprime une étape 'stag' via son id
     * @param id
     * @return ResponseEntity<Boolean>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@RequestBody String id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }
}
