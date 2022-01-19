package com.mycompany.roadtripplanner.repositories.AuthRepository;


import com.mycompany.roadtripplanner.entities.roles.ERole;
import com.mycompany.roadtripplanner.entities.roles.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);


}
