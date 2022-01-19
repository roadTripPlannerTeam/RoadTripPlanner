package com.mycompany.roadtripplanner.repositories.AuthRepository;

;
import com.mycompany.roadtripplanner.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);

  //Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
