package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.interestpoint.*;
import com.mycompany.roadtripplanner.entities.InterestPoint;
import com.mycompany.roadtripplanner.repositories.InterestPointRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class InterestPointService {

    InterestPointRepositoryImpl repository;
    ModelMapper mapper;

    /**
     * Constructeur de InterestPointService
     * Injection de dépendance
     * @param repository
     * @param mapper
     */
    public InterestPointService(ModelMapper mapper, InterestPointRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }
    
    /**
     * Va retourner une liste de tous les interestPoints
     * @return liste interestPointDTOs
     */
    public List<InterestPointDTO> findAll(){
        List<InterestPointDTO> interestPointDTOS = new ArrayList<>();
        this.repository.findAll().forEach(interestPoint -> {
            interestPointDTOS.add(mapper.map(interestPoint, InterestPointDTO.class));
        });
        return interestPointDTOS;
    }

    /**
     * Va retourner un interestPoint grâce à son id
     * @param id
     * @return l'objet interestPointDTO
     */
    public Optional<InterestPointDTO> find(String id) throws NoSuchElementException {
        Optional<InterestPoint> interestPointOptional = repository.findById(id);
        Optional<InterestPointDTO> interestPointDTO = null;
        if(interestPointOptional.isPresent()){
            interestPointDTO = Optional.of(mapper.map(interestPointOptional.get(), InterestPointDTO.class));
        } else {
            throw new NoSuchElementException("Ce interestPoint n'existe pas");
        }
        return  interestPointDTO;
    }

    /**
     * Va créer un nouvel objet interestPoint
     * @param obj
     * @return l'objet interestPoint créé
     */
    public InterestPointGetSaveDTO save(InterestPointSaveDTO obj){
        return mapper.map(repository.save(mapper.map(obj, InterestPoint.class)), InterestPointGetSaveDTO.class);
    }

    /**
     * Va modifier l'objet interestPoint
     * @param interestPointUpdateDTO
     * @return l'objet interestPoint modifié
     */
    public InterestPointDTO update(InterestPointUpdateDTO interestPointUpdateDTO){
        return mapper.map(repository.save(mapper.map(interestPointUpdateDTO, InterestPoint.class)), InterestPointDTO.class);
    }

    /**
     * Va supprimer un objet interestPoint via son objet
     * @param  interestPointDeleteDTO
     */
    public void delete(InterestPointDeleteDTO interestPointDeleteDTO){
        repository.delete( mapper.map(interestPointDeleteDTO, InterestPoint.class));
    }
}