package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.Location;

/**
 * Author: Solodoukhin Viktor
 * Date: 17.10.18
 * Description: Location entity for API methods
 */
public class LocationDTO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
