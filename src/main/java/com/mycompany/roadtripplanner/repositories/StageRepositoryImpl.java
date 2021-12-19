package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.stage.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepositoryImpl extends MongoRepository<Stage, String> {
}
