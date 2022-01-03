package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointDTO;
import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointDeleteDTO;
import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointSaveDTO;
import com.mycompany.roadtripplanner.dtos.interestpoint.InterestPointUpdateDTO;
import com.mycompany.roadtripplanner.services.InterestPointService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/interestpoints")
public class InterestPointController {

    InterestPointService service;
    ModelMapper mapper;

    /**
     * Constructeur de InterestPointController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public InterestPointController(InterestPointService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir tous les interestPoints
     * @return liste de interestPoint
     */
    @GetMapping
    public List<InterestPointDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir un interestPoint selon son Id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("{id}")
    public ResponseEntity<InterestPointDTO> find(@PathVariable String id){
        try{
            return ResponseEntity.ok(service.find(id).get());
        } catch(NoSuchElementException e){
            return ResponseEntity.notFound().header(e.getMessage()).build();
        }
    }

    /**
     * Créé la route pour créer un objet interestPoint
     * @param interestPointSaveDTO
     * @return un statut Http 201 Created
     */
    @PostMapping
    public ResponseEntity<InterestPointDTO> save(@RequestBody InterestPointSaveDTO interestPointSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(interestPointSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet interestPoint
     * @param interestPointUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<InterestPointDTO> update(@RequestBody InterestPointUpdateDTO interestPointUpdateDTO){
        return ResponseEntity.ok(service.update(interestPointUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet interestPoint via son objet
     * @param interestPointDeleteDTO
     * @return un statut Http 200 ok true
     */
    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestBody InterestPointDeleteDTO interestPointDeleteDTO){
        service.delete(interestPointDeleteDTO);
        return ResponseEntity.ok("interestPoint bien supprimé ");
    }
}