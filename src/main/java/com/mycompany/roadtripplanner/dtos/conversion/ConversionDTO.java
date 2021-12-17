package com.mycompany.roadtripplanner.dtos.conversion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionDTO {

    private String id;
    private float initialSum;
    private float rate;
    private String symbolCurrency;

}
