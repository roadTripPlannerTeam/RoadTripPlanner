package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryImpl extends MongoRepository<Comment,String> {
}
