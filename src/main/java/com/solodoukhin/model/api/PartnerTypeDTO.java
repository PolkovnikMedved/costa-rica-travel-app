package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.PartnerType;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.10.18
 * Description: Partner Type method for api methods
 */
public class PartnerTypeDTO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("LogoAddress")
    private String picture;

    @JsonProperty("Country")
    private String country;

    public PartnerTypeDTO(PartnerType partnerType) {
        this.id = partnerType.getId();
        this.name = partnerType.getName();
        this.picture = partnerType.getPicture();
        this.country = partnerType.getCountry();
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
