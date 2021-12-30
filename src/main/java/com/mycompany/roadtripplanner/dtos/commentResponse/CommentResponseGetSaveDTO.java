package com.mycompany.roadtripplanner.dtos.commentResponse;

import com.mycompany.roadtripplanner.dtos.comment.CommentRelationDTO;
import com.mycompany.roadtripplanner.dtos.itinearay.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.user.UserRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseGetSaveDTO {
    private String id;
    private String title;
    private String description;
    private Date date;
    private int like;
    private UserRelationDTO user;
    private ItineraryRelationDTO itinerary;
    private CommentRelationDTO comment;

}