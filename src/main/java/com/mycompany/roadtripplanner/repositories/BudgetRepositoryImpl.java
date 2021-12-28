package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepositoryImpl extends MongoRepository<Budget, String> {
}
