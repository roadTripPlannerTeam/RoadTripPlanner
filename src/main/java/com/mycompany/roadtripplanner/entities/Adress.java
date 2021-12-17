package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String number;
    private String street;
    private int postal_code;
    private String city;
    private String country;
}
