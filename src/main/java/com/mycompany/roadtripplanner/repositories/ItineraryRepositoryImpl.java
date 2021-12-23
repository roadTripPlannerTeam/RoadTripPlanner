package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ItineraryRepositoryImpl extends MongoRepository<Itinerary,String> {
}
