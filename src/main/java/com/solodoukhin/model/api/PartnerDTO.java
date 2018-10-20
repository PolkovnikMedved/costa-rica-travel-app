package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.HotWord;
import com.solodoukhin.model.Partner;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.10.18
 * Description: Partner model for api methods
 */
public class PartnerDTO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Address")
    private String address;

    @JsonProperty("PictureServerAddress")
    private String picture;

    @JsonProperty("Latitude")
    private Double latitude;

    @JsonProperty("Longitude")
    private Double longitude;

    @JsonProperty("Horary")
    private String horary;

    @JsonProperty("Comment")
    private String comment;

    @JsonProperty("IsSpecialOffert")
    private boolean specialOffer;

    @JsonProperty("Offert")
    private String offer;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("TripAdvisorLink")
    private String tripAdvisorLink;

    @JsonProperty("TypeID")
    private int typeId;

    @JsonProperty("Type")
    private String typeName;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("HotWords")
    private Set<String> hotWords;

    @JsonProperty("ServerImageAddress")
    private Set<String> pictures = new HashSet<>();

    public PartnerDTO(Partner partner) {
        this.id = partner.getId();
        this.name = partner.getName();
        this.email = partner.getEmail();
        this.phone = partner.getPhone();
        this.address = partner.getAddress();
        this.picture = partner.getPicture();
        this.latitude = partner.getLatitude();
        this.longitude = partner.getLongitude();
        this.horary = partner.getHorary();
        this.comment = partner.getComment();
        this.specialOffer = partner.isSpecialOffer();
        this.offer = partner.getOffer();
        this.location = partner.getLocation().getName();
        this.tripAdvisorLink = partner.getTripAdvisorLink();
        this.typeId = partner.getType().getId();
        this.typeName = partner.getType().getName();
        this.country = partner.getCountry();
        this.hotWords = new HashSet<>();
        for(HotWord hw : partner.getHotWords())
        {
            this.hotWords.add(hw.getName());
        }
        //TODO this.pictures = new HashSet<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getHorary() {
        return horary;
    }

    public void setHorary(String horary) {
        this.horary = horary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(boolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTripAdvisorLink() {
        return tripAdvisorLink;
    }

    public void setTripAdvisorLink(String tripAdvisorLink) {
        this.tripAdvisorLink = tripAdvisorLink;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<String> getHotWords() {
        return hotWords;
    }

    public void setHotWords(Set<String> hotWords) {
        this.hotWords = hotWords;
    }

    public Set<String> getPictures() {
        return pictures;
    }

    public void setPictures(Set<String> pictures) {
        this.pictures = pictures;
    }
}
