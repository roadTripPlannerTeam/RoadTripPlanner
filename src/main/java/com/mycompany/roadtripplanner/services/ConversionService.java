package com.mycompany.roadtripplanner.services;

import com.mycompany.roadtripplanner.dtos.conversion.ConversionDTO;
import com.mycompany.roadtripplanner.dtos.conversion.ConversionSaveDTO;
import com.mycompany.roadtripplanner.dtos.conversion.ConversionUpdateDTO;
import com.mycompany.roadtripplanner.entities.Conversion;
import com.mycompany.roadtripplanner.repositories.ConversionRepositoryImpl;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConversionService {

    ConversionRepositoryImpl repository;
    ModelMapper mapper;

    public ConversionService(ModelMapper mapper, ConversionRepositoryImpl repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    /**
     * Constructeur de ConversionService
     * Injection de dépendance
     * @param repository
     * @param mapper
     */
    public ConversionService(ConversionRepositoryImpl repository, ModelMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Va retourner une liste de conversion
     * @return liste conversionDTOs
     */
    public List<ConversionDTO> findAll(){
        List<ConversionDTO> conversionDTOS = new ArrayList<>();
        this.repository.findAll().forEach(conversion -> {
            conversionDTOS.add(mapper.map(conversion, ConversionDTO.class));
        });
        return conversionDTOS;
    }

    /**
     * Va retourner une conversion grâce à son Id
     * @param id
     * @return un objet conversionDTO
     */
    public ConversionDTO find(String id){
        Optional<Conversion> c = repository.findById(id);
        ConversionDTO conversionDTO = null;
        if(c.isPresent()){
            conversionDTO = mapper.map(c.get(), ConversionDTO.class);
        }
        return conversionDTO;
    }

    /**
     * Va créer un nouvel objet conversion
     * @param obj
     * @return l'objet conversion créé
     */
    public ConversionDTO save(ConversionSaveDTO obj){
        Conversion conversionToSave = mapper.map(obj, Conversion.class);
        Conversion conversion = repository.save(conversionToSave);
        ConversionDTO conversionSaved = mapper.map(conversion, ConversionDTO.class);
        return conversionSaved;
    }

    /**
     * Va modifier l'objet conversion
     * @param obj
     * @return l'objet conversion modifié
     */
    public ConversionDTO update(ConversionUpdateDTO obj){
        Conversion conversionToUpdate = mapper.map(obj, Conversion.class);
        Conversion conversion = repository.save(conversionToUpdate);
        ConversionDTO conversionSaved = mapper.map(conversion, ConversionDTO.class);
        return conversionSaved;
    }

    /**
     * Va supprimer un objet conversion grâce à son id
     * @param id
     */
    public void delete(String id){
        repository.deleteById(id);
    }

}
