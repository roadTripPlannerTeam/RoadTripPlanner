package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entité Conversion
 */

@Document
@AllArgsConstructor
@NoArgsConstructor
public class Conversion {

    @Id
    private String Id;
    private float intialSum;
    private float rate;
    private String symbolCurrency;

}
