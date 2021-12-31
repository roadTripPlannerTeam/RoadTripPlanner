package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Itinerary;
import com.mycompany.roadtripplanner.entities.Stage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface  ItineraryRepositoryImpl extends MongoRepository<Itinerary,String> {

    List<Itinerary> findItinerariesByUser_Id(String id);
}
