package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.InterestPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestPointRepositoryImpl extends MongoRepository<InterestPoint,String> {

    List<InterestPoint> findInterestPointsByUnknownStage_Id(String id );

}
