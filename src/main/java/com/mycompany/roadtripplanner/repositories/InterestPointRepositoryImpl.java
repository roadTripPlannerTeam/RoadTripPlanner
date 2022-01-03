package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.InterestPoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InterestPointRepositoryImpl extends MongoRepository<InterestPoint,String> {
}
