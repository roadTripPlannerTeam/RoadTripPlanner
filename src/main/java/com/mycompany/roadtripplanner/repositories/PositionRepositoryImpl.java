package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Position;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface PositionRepositoryImpl extends MongoRepository<Position,String > {
}
