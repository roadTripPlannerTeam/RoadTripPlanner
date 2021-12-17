package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.conversion.ConversionDTO;
import com.mycompany.roadtripplanner.dtos.conversion.ConversionSaveDTO;
import com.mycompany.roadtripplanner.dtos.conversion.ConversionUpdateDTO;
import com.mycompany.roadtripplanner.services.ConversionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversions")
public class ConversionController {

    ConversionService service;
    ModelMapper mapper;

    /**
     * Constructeur de ConversionController
     * Injection de dépendance
     * @param service
     * @param mapper
     */
    public ConversionController(ConversionService service, ModelMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    /**
     * Créé la route pour recevoir toutes les conversions
     * @return la liste de toutes les conversions
     */
    @GetMapping
    public List<ConversionDTO> findAll(){
        return service.findAll();
    }

    /**
     * Créé la route pour recevoir une conversion selon son id
     * @param id
     * @return un statut Http 200 ok
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConversionDTO> find(@PathVariable String id){
        ConversionDTO conversionDTO = service.find(id);
        if(conversionDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(conversionDTO);
    }

    /**
     * Créé la route pour créer un objet conversion
     * @param conversionSaveDTO
     * @return un statut Http 201 created
     */
    @PostMapping
    public ResponseEntity<ConversionDTO> save(@RequestBody ConversionSaveDTO conversionSaveDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conversionSaveDTO));
    }

    /**
     * Créé la route pour modifier un objet conversion déjà existant
     * @param conversionUpdateDTO
     * @return un statut Http 200 ok
     */
    @PutMapping
    public ResponseEntity<ConversionDTO> update(@RequestBody ConversionUpdateDTO conversionUpdateDTO){
        return ResponseEntity.ok(service.update(conversionUpdateDTO));
    }

    /**
     * Créé la route pour supprimer un objet conversion via son Id
     * @param id
     * @return un statut Http 200 ok true
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok(true);
    }
}