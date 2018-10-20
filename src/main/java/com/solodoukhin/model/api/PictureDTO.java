package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.Picture;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.10.18
 * Description: Picture model for api methods
 */
public class PictureDTO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Address")
    private String address;

    public PictureDTO(Picture picture) {
        this.id = picture.getId();
        this.address = picture.getAddress();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
