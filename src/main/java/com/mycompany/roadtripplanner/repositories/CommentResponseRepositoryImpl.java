package com.mycompany.roadtripplanner.repositories;

import com.mycompany.roadtripplanner.entities.Comment;
import com.mycompany.roadtripplanner.entities.CommentResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentResponseRepositoryImpl extends MongoRepository<CommentResponse,String> {
         List<CommentResponse >  findCommentResponsesByComment_Id(String id );
}
