package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryGetSaveDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryDeleteDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItinerarySaveDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryUpdateDTO;

import com.mycompany.roadtripplanner.dtos.stage.StageGetFindAllDTO;
import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.entities.Stage;
import com.mycompany.roadtripplanner.repositories.CommentRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.ItineraryRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import org.modelmapper.ModelMapper;


import java.util.*;

public class ItineraryService {

    private ModelMapper mapper;
    private ItineraryRepositoryImpl repository;
    private CommentRepositoryImpl commentrepository;
    private StageRepositoryImpl stageRepository;


    /**
     * Constructeur pour le mapper et l'interface repository
     * @param mapper
     * @param repository
     * @param commentrepository
     * @param stageRepository
     */

    public ItineraryService(ModelMapper mapper, ItineraryRepositoryImpl repository, CommentRepositoryImpl commentrepository, StageRepositoryImpl stageRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.commentrepository = commentrepository;
        this.stageRepository = stageRepository;

    }

    /**
     *
     * @param itinerarySaveDTO
     * @return ItineraryGetSaveDTO itineraryGetSaveDTO
     */
    public ItineraryGetSaveDTO save(ItinerarySaveDTO itinerarySaveDTO) {
        return   mapper.map(repository.save(mapper.map(itinerarySaveDTO,Itinerary.class)), ItineraryGetSaveDTO.class);

    }

    /**
     * list to map  .
     * @param stages List<Stage>
     * @return Map <Date, Stage>
     */

    private Map<Date, Stage> convertListToMap(List<Stage> stages) {
        Map<Date, Stage> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getDate(), stage);
        }
        return map;
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
            List<Stage> stageList = stageRepository.findStagesByItinerary_Id(itinerarie.getId());
            itinerarie.setStages( this.convertListToMap(stageList));
            itinerarie.setComments( commentrepository.findCommentsByItinerary_Id(itinerarie.getId()));
            ItineraryDTO itineraryDTO= mapper.map(itinerarie,ItineraryDTO.class);
            Map<Date, StageGetFindAllDTO> itineraryDTOStages= itineraryDTO.getStages();
            Map<Date, StageGetFindAllDTO> treeMap = new TreeMap<>();
            treeMap.putAll(itineraryDTOStages);
            itineraryDTO.setStages(treeMap);
            itineraries.add(itineraryDTO);
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
    public Optional<ItineraryDTO> find(String id) throws NoSuchElementException {
        Optional<Itinerary>itineraryOptional =repository.findById(id);
        Optional<ItineraryDTO>itineraryDTO = null;

        if (itineraryOptional.isPresent()) {
            itineraryDTO = Optional.of(mapper.map(itineraryDTO.get(),ItineraryDTO.class));
        }else{
            throw new NoSuchElementException("itinerary is not found");
        }
        return itineraryDTO;
    }

    /**
     * Méthode permettant de modifier les informations d'un itineraire
     * @param itineraryUpdateDTO
     * elle transformera les informations modifié qui les enverra au repository qui les sauvegardera
     * Elle transformera l'objet recupérer du repository
     * @return un itineraire avec les informations modifier
     */
    public Object update(ItineraryUpdateDTO itineraryUpdateDTO) {
        return mapper.map(repository.save(mapper.map(itineraryUpdateDTO,Itinerary.class)), ItineraryDTO.class);

    }

    /**
     * Méthode qui supprimera l'itineraire'
     * @param itineraryDeleteDTO
     * Elle envoie au repository la requête a supprimé qui possède cette id
     */
    public void delete(ItineraryDeleteDTO itineraryDeleteDTO) {
        repository.delete(mapper.map(itineraryDeleteDTO,Itinerary.class));
    }
}