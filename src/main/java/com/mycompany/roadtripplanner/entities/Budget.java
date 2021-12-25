package com.mycompany.roadtripplanner.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Entité Budget
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget {

    @Id
    private String id;
    private float initialAmount;
    private float expense;

}
