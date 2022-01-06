package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.unknownstage.*;
import com.mycompany.roadtripplanner.entities.InterestPoint;
import com.mycompany.roadtripplanner.entities.UnknownStage;
import com.mycompany.roadtripplanner.repositories.InterestPointRepositoryImpl;
import com.mycompany.roadtripplanner.repositories.UnknownStageRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UnknownStageService {

    private UnknownStageRepositoryImpl repository;
    private InterestPointRepositoryImpl interestPointRepository;
    private ModelMapper mapper;

    /**
     * Constructeur de UnknownStageService
     * Injection de dépendance
     * @param repository
     * @param mapper
     */
    public UnknownStageService(ModelMapper mapper, UnknownStageRepositoryImpl repository, InterestPointRepositoryImpl interestPointRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.interestPointRepository = interestPointRepository;
    }

    /**
     * Va retourner une liste de tous les unknownStages
     * @return liste unknownStageDTOs
     */
    public List<UnknownStageDTO> findAll(){
        List<UnknownStageDTO> unknownStageDTOS = new ArrayList<>();
        repository.findAll().forEach(unknownStage -> {
            List<InterestPoint> interestPointsByUnknownStageid = interestPointRepository.findInterestPointsByUnknownStage_Id(unknownStage.getId());
            unknownStage.setInterestPoints(interestPointsByUnknownStageid);
            unknownStageDTOS.add(mapper.map(unknownStage, UnknownStageDTO.class));
        });
        return unknownStageDTOS;
    }

    /**
     * Va retourner un unknownStage grâce à son id
     * @param id
     * @return l'objet unknownStageDTO
     */
    public Optional<UnknownStageDTO> find(String id) throws NoSuchElementException {
        Optional<UnknownStage> unknownStageOptional = repository.findById(id);
        Optional<UnknownStageDTO> unknownStageDTO = null;
        if(unknownStageOptional.isPresent()){
            unknownStageDTO = Optional.of(mapper.map(unknownStageOptional.get(), UnknownStageDTO.class));
        } else {
            throw new NoSuchElementException("Ce unknownStage n'existe pas");
        }
        return  unknownStageDTO;
    }

    /**
     * Va créer un nouvel objet unknownStage
     * @param obj
     * @return l'objet unknownStage créé
     */
    public UnknownStageGetSaveDTO save(UnknownStageSaveDTO obj){
        return mapper.map(repository.save(mapper.map(obj, UnknownStage.class)), UnknownStageGetSaveDTO.class);
    }

    /**
     * Va modifier l'objet unknownStage
     * @param unknownStageUpdateDTO
     * @return l'objet unknownStage modifié
     */
    public UnknownStageDTO update(UnknownStageUpdateDTO unknownStageUpdateDTO){
        UnknownStageDTO unknownStageSaved = mapper.map(repository.save(mapper.map(unknownStageUpdateDTO, UnknownStage.class)), UnknownStageDTO.class);
        return unknownStageSaved;
    }

    /**
     * Va supprimer un objet unknownStage grâce à son Id
     * @param  unknownStageDeleteDTO
     */
    public void delete(UnknownStageDeleteDTO unknownStageDeleteDTO){
        repository.delete(mapper.map(unknownStageDeleteDTO,UnknownStage.class));
    }
}