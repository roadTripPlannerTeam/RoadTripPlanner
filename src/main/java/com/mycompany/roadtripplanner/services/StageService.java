package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.stage.*;
import com.mycompany.roadtripplanner.dtos.stage.StageGetDTO;
import com.mycompany.roadtripplanner.entities.Stage;
import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class StageService {
    StageRepositoryImpl repository;
    ModelMapper mapper;

    /**
     * Constructeur
     *
     * @param mapper
     * @param repository
     */
    public StageService(ModelMapper mapper, StageRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * retourne la liste de toutes les étapes 'stages'
     *
     * @return List<StageGetDTO> stageDTOS
     */
    public List<StageGetDTO> findAll() {
        List<StageGetDTO> stageDTOS = new ArrayList<>();
        this.repository.findAll().forEach(stage -> {
            stageDTOS.add(mapper.map(stage, StageGetDTO.class));
        });
        return stageDTOS;
    }

    /**
     * retourne une étape 'stage' via son id
     *
     * @param id
     * @return Optional<StageGetDTO> stageDTO
     */
    public Optional<StageGetDTO> findById(String id) throws NoSuchElementException {
        Optional<Stage> stage = repository.findById(id);
        StageGetDTO stageDTO = null;
        if(stage.isPresent()){
            stageDTO =   mapper.map( repository.findById(id).get(), StageGetDTO.class);
        }

        return Optional.of(stageDTO);
    }

    /**
     * Création et sauvegarde d'une étape 'stage'
     *
     * @param stageSaveDTO
     * @return StageGetDTO stageSaved
     */
    public StageGetSaveDTO save(StageSaveDTO stageSaveDTO) {
      return  mapper.map( repository.save( mapper.map(stageSaveDTO, Stage.class)), StageGetSaveDTO.class);

    }

    /**
     * Mise à jour et modification d'une étape 'stage'
     *
     * @param stageUpdateDTO
     * @return StageGetDTO stageSaved
     */
    public StageGetDTO update(StageUpdateDTO stageUpdateDTO) {
        Stage stageToSave = mapper.map(
                stageUpdateDTO,
                Stage.class
        );
        Stage stage = repository.save(stageToSave);
        StageGetDTO stageSaved = mapper.map(stage, StageGetDTO.class);
        return stageSaved;
    }

    /**
     *
     * @param stageDeleteDTO
     */
    public void delete(StageDeleteDTO stageDeleteDTO) {
        repository.delete( mapper.map(stageDeleteDTO,Stage.class));  ;
    }
}
