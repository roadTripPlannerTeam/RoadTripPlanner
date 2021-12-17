package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends MongoRepository<User,String> {

}
