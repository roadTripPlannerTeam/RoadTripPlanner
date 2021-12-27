package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Itinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  ItineraryRepositoryImpl extends MongoRepository<Itinerary,String> {

    List<Itinerary> findItinerariesByUser_Id(String id);
}
