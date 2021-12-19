package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.stage.StageDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageSaveDTO;
import com.mycompany.roadtripplanner.dtos.stage.StageUpdateDTO;
import com.mycompany.roadtripplanner.entities.Stage;
import com.mycompany.roadtripplanner.repositories.StageRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StageService {
    StageRepositoryImpl repository;
    ModelMapper mapper;

    /**
     * Constructeur
     * @param mapper
     * @param repository
     */
    public StageService(ModelMapper mapper, StageRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * retourne la liste de toutes les étapes 'stages'
     * @return List<StageDTO> stageDTOS
     */
    public List<StageDTO> findAll() {
        List<StageDTO> stageDTOS = new ArrayList<>();
        this.repository.findAll().forEach(stage -> {
            stageDTOS.add(mapper.map(stage, StageDTO.class));
        });
        return stageDTOS;
    }

    /**
     * retourne une étape 'stage' via son id
     * @param id
     * @return stageDTO
     */
    public StageDTO find(String id) {
        Optional<Stage> stageOptional = repository.findById(id);
        StageDTO stageDTO = null;
        if (stageOptional.isPresent()) {
            stageDTO = mapper.map(stageOptional, StageDTO.class);
        }
        return stageDTO;
    }

    /**
     * Création et sauvegarde d'une étape 'stage'
     * @param stageSaveDTO
     * @return stageSaved
     */
    public StageDTO save(StageSaveDTO stageSaveDTO) {
        Stage stageToSave = mapper.map(
                stageSaveDTO,
                Stage.class
        );
        Stage stage = repository.save(stageToSave);
        StageDTO stageSaved = mapper.map(stage, StageDTO.class);
        return stageSaved;
    }

    /**
     * Mise à jour et modification d'une étape 'stage'
     * @param stageUpdateDTO
     * @return stageSaved
     */
    public StageDTO update(StageUpdateDTO stageUpdateDTO) {
        Stage stageToSave = mapper.map(
                stageUpdateDTO,
                Stage.class
        );
        Stage stage = repository.save(stageToSave);
        StageDTO stageSaved = mapper.map(stage, StageDTO.class);
        return stageSaved;
    }

    /**
     * Supprime une étape 'stage' via son id
     * @param id
     */
    public void delete(String id) {
        repository.deleteById(id);
    }
}
