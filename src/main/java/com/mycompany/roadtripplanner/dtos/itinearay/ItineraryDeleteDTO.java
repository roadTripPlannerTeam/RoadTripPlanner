package com.mycompany.roadtripplanner.dtos.itinearay;

import com.mycompany.roadtripplanner.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDeleteDTO {

    private String id;

}
