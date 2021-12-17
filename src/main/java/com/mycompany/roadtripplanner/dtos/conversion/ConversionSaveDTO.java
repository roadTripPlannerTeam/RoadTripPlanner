package com.mycompany.roadtripplanner.dtos.conversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionSaveDTO {

    private float initialSum;
    private float rate;
    private String symbolCurrency;

}
