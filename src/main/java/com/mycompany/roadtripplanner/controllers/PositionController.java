package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.position.*;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
@CrossOrigin
public class PositionController {

    PositionService service;

    public PositionController(PositionService service) {
        this.service = service;
    }

    /**
     * Accès à toutes les positions
     *
     * @return List<PositionGetDTO>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PositionGetDTO> findAll() {
        return service.findAll();
    }

    /**
     * Accès à une position via son id
     *
     * @param id
     * @return ResponseEntity<PositionGetDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<PositionGetDTO> find(@PathVariable String id) {
        try{
            Optional<PositionGetDTO> positionDTO = service.findById(id);
            return (ResponseEntity.ok(positionDTO.get()));

        }catch (NoSuchElementException e ){
            return (ResponseEntity.notFound().header(e.getMessage()).build());
        }
    }

    /**
     * Création et sauvegarde d'une position
     *
     * @param position
     * @return ResponseEntity<PositionGetDTO>
     */
    @PostMapping
    public ResponseEntity<PositionGetDTO> create(@RequestBody PositionSaveDTO position) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(position));
    }

    /**
     * Modification d'une position
     *
     * @param position
     * @return ResponseEntity<PositionGetDTO>
     */
    @PutMapping
    public ResponseEntity<PositionGetDTO> update(@RequestBody PositionUpdateDTO position) {
        return ResponseEntity.ok(service.update(position));
    }

    /**
     * Supprime une Position via son objet
     *
     * @return ResponseEntity<String>
     */
    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody PositionDeleteDTO position) {
        service.delete(position);
        return ResponseEntity.ok("position bien supprimée");
    }


}
