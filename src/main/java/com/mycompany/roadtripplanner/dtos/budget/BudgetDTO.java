package com.mycompany.roadtripplanner.dtos.budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    private String id;
    private float initialAmount;
    private float expense;

}
