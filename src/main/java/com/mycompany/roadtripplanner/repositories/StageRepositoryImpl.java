package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface StageRepositoryImpl extends MongoRepository<Stage, String> {

    List<Stage> findStagesByItinerary_Id(String id );

}
