package com.mycompany.roadtripplanner.services;


import com.mycompany.roadtripplanner.dtos.position.PositionDeleteDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionGetDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionSaveDTO;
import com.mycompany.roadtripplanner.dtos.position.PositionUpdateDTO;
import com.mycompany.roadtripplanner.entities.Position;
import com.mycompany.roadtripplanner.repositories.PositionRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class PositionService {

    private PositionRepositoryImpl repository ;
    private ModelMapper mapper;

    public PositionService(PositionRepositoryImpl  repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.mapper = modelMapper;
    }

    /**
     * retourne la liste positions
     * @return List<PositionGetDTO>
     */
    public List<PositionGetDTO> findAll(){
        List<PositionGetDTO> positionDTOList = new ArrayList<>();
        this.repository.findAll().forEach(position-> positionDTOList.add(mapper.map(position, PositionGetDTO.class)));
        return positionDTOList;
    }


    /**
     * permet de retrouver une position avec un id
     * @param id String
     * @return Optional<PositionGetDTO>
     */
    public Optional<PositionGetDTO> findById(String id) throws NoSuchElementException {
        Optional<Position> position = repository.findById(id);
        PositionGetDTO positionDTO = null;
        if(position.isPresent()){
         positionDTO =   mapper.map( repository.findById(id).get(), PositionGetDTO.class);
        }

        return Optional.of(positionDTO);
    }


    /**
     * permet de persister une position avec un objet
     * @param positionDTO PositionDTO
     * @return PositionGetDTO Object .
     */
    public PositionGetDTO save(PositionSaveDTO positionDTO )  {
        return mapper.map(repository.save(mapper.map(positionDTO , Position.class)), PositionGetDTO.class);
    }

    /**
     * update position .
     * @param positionUpdateDTO
     * @return PositionGetDTO positionGetDTO
     */
    public PositionGetDTO update(PositionUpdateDTO positionUpdateDTO )  {
        return mapper.map(repository.save(mapper.map(positionUpdateDTO , Position.class)), PositionGetDTO.class);
    }

    /**
     * permet delete une position  avec un objet
     * @param positionDeleteDTO positionDeleteDTO
     * */
    public void delete(PositionDeleteDTO positionDeleteDTO)  {
        repository.delete( mapper.map(positionDeleteDTO , Position.class));
    }

}
