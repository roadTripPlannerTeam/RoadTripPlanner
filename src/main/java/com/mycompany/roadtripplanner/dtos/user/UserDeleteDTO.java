package com.mycompany.roadtripplanner.dtos.user;

import java.util.Date;

public class UserDeleteDTO {
    //Attribut
    private String id;

    //Constructeurs
    public UserDeleteDTO() {
    }

    public UserDeleteDTO(String id) {
        this.id = id;
    }

    //Getter Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
