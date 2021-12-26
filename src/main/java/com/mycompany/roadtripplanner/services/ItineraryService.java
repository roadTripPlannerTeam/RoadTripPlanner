package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryGetSaveDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItinerarySaveDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryUpdateDTO;
import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param itinerarySaveDTO
     * @return
     */
    public ItineraryGetSaveDTO save(ItinerarySaveDTO itinerarySaveDTO) {
      return   mapper.map(repository.save(mapper.map(itinerarySaveDTO,Itinerary.class)), ItineraryGetSaveDTO.class);

    }

    /**
     * Méthode qui permet de récuperer une liste d'itineraire
     *Elle crée une lise d'itineraire
     * Elle effectue une boucle pour transformer l'ensemble des itineraire trouvé dans le repository
     * Tant qu'il y en a  pour les inserer dans la liste
     * @return une liste avec l'ensemble des itineraires
     */
    public List<ItineraryDTO> findAll() {
        List<ItineraryDTO>itineraries = new ArrayList<>();
        repository.findAll().forEach(itinerarie -> {
            System.out.println(itinerarie);
            itineraries.add(mapper.map(itinerarie,ItineraryDTO.class));
        });
        return itineraries;
    }

    /**
     * Méthode qui permet de récupere les informations d'un itineraire
     * @param id
     * La requête va recupérer l'itineraire qui possède cette id passé en paramètre
     * On instancie le itineraryDTO a null puis dans notre condition Si notre optionnal n'est vide
     * nous passerons ses valeur dans le ItineraryDto
     * @return les informations d'un itineraire'
     */
    public Itinerary find(String id) {
        Optional<Itinerary>itineraryOptional =repository.findById(id);
        Itinerary itineraryDto = null;
        if (itineraryOptional.isPresent()) {
            itineraryDto = itineraryOptional.get();
        }
        return itineraryDto;
    }

    /**
     * Méthode permettant de modifier les informations d'un itineraire
     * @param itineraryUpdateDTO
     * elle transformera les informations modifié qui les enverra au repository qui les sauvegardera
     * Elle transformera l'objet recupérer du repository
     * @return un itineraire avec les informations modifier
     */
    public Object update(ItineraryUpdateDTO itineraryUpdateDTO) {
        Itinerary itineraryToSave = mapper.map(itineraryUpdateDTO,Itinerary.class);
        Itinerary itinerarySaving =repository.save(itineraryToSave);
        ItineraryDTO itineraryRetour = mapper.map(itinerarySaving, ItineraryDTO.class);
        return itineraryRetour;
    }

    /**
     * Méthode qui supprimera l'itineraire'
     * @param id
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
