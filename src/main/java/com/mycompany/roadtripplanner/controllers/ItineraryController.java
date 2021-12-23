package com.mycompany.roadtripplanner.controllers;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItinerarySaveDTO;
import com.mycompany.roadtripplanner.services.ItineraryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("itinerary")
public class ItineraryController {

    private ItineraryService service;
    public ItineraryController(ItineraryService service){
        this.service=service;
    }

    /**
     * Controlleur qui demande au service de créer un itinaire
     * avec les données du Request body tranmist
     * @param itinerary
     * @return un itinéraire sauvegardé
     */
    @PostMapping()
    public ItineraryDTO save(@RequestBody ItinerarySaveDTO itinerary){
        return service.save(itinerary);
    }
}
