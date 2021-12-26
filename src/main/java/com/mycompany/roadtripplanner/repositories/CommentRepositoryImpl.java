package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositoryImpl extends MongoRepository<Comment,String> {

    List<Comment> findCommentsByUserId(String id );
}
