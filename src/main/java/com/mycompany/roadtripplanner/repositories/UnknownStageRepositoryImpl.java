package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Budget;
import com.mycompany.roadtripplanner.entities.UnknownStage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnknownStageRepositoryImpl extends MongoRepository<UnknownStage, String> {
}
