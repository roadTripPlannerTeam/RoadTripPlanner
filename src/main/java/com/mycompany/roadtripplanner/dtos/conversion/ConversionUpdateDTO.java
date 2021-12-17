package com.mycompany.roadtripplanner.dtos.conversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionUpdateDTO {

    private String Id;
    private float intialSum;
    private float rate;
    private String symbolCurrency;

}
