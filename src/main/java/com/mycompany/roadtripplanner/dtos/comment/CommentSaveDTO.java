package com.mycompany.roadtripplanner.dtos.comment;

import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryRelationDTO;
import com.mycompany.roadtripplanner.dtos.user.UserRelationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveDTO {
    private String title;
    private String description;
    private Date date;
    private int like;
    private UserRelationDTO user;
    private ItineraryRelationDTO itinerary;
}
