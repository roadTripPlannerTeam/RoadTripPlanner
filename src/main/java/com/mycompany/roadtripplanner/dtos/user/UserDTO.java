package com.mycompany.roadtripplanner.dtos.user;

import com.mycompany.roadtripplanner.dtos.comment.CommentGetDTO;
import com.mycompany.roadtripplanner.dtos.itinerary.ItineraryGetDTO;
import com.mycompany.roadtripplanner.entities.Adress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthday;
    private Adress adress;
    private String profilPicture;
    private List<CommentGetDTO> comments ;
    private List<ItineraryGetDTO> itineraries ;

}
