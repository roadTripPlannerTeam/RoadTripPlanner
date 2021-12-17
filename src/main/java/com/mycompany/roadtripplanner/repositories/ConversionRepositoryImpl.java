package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Conversion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversionRepositoryImpl extends MongoRepository<Conversion, String> {
}
