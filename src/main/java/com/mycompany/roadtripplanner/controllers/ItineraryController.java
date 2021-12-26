package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryGetSaveDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItinerarySaveDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryUpdateDTO;
import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.services.ItineraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itinerary")
public class ItineraryController {

    private ItineraryService service;
    public ItineraryController(ItineraryService service){
        this.service=service;
    }

    /**
     * Controlleur qui demande au service de créer un itineraire
     * avec les données du Request body tranmist
     * @param itinerary
     * @return un itinéraire sauvegardé
     */
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ItineraryGetSaveDTO save(@RequestBody ItinerarySaveDTO itinerary){
        return service.save(itinerary);
    }

    /**
     * Controlleur qui demande au service une list de itineraire
     * @return une liste d'itineraire
     */
    @GetMapping()
    public List<ItineraryDTO> findAll(){
        return service.findAll();
    }

    /**
     * Constructeur Qui demande au service de trouver un itineraire par l'id donnée en path variable
     * @param id qui doit être récupere par le path varible
     * @return un message d'erreur si l'id n'existe pas
     * @return l'itineraire avec c'est information si le commentaire existe
     */
    @GetMapping("{id}")
    public ResponseEntity<Itinerary> find(@PathVariable String id){
        Itinerary itinerary = service.find(id);
        if(itinerary == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(itinerary);
    }

    /**
     * Controlleur qui demande au service de modifier un itineraire avec
     * les informations donnée par le request body
     * @param itineraryUpdateDTO
     * @return une reponse du service si la modification est un succès avec les nouvelles informations
     */

    @PutMapping()
    public ResponseEntity<Object>update(@RequestBody ItineraryUpdateDTO itineraryUpdateDTO){
        return ResponseEntity.ok(service.update(itineraryUpdateDTO));
    }

    /**
     * Controlleur qui demande au service de supprimer un itineraire par l'id donné dans le path variable
     * @param id
     * @return une réponse true si l'itineraire' à bien était suppprimé
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id){
        service.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
