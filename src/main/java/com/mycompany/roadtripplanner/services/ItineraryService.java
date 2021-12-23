package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItinerarySaveDTO;
import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItineraryService {

    private ModelMapper mapper;
    private ItineraryRepositoryImpl repository;

    /**
     * Constructeur pour le mapper et l'interface repository
     * @param mapper
     * @param repository
     */
    public ItineraryService(ModelMapper mapper, ItineraryRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     *
     * @param itinerary
     * @return
     */
    public ItineraryDTO save(ItinerarySaveDTO itineraryObj) {
        Itinerary itineraryToSave = mapper.map(itineraryObj,Itinerary.class);
        Itinerary itinerary =repository.save(itineraryToSave);
        ItineraryDTO itinerarySaved = mapper.map(itinerary, ItineraryDTO.class);
        return itinerarySaved;
    }
}
