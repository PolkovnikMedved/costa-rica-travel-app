package com.solodoukhin.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Solodoukhin Viktor
 * Date: 14.07.18
 * Description: TODO
 */
@Entity
@Table(name = "partner")
public class Partner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(name = "picture_server_address")
    private String picture;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String horary;

    @Column
    private String comment;

    @Column(name = "is_special_offer")
    private boolean specialOffer;

    @Column
    private String offer;

    @ManyToOne
    @JoinColumn(name = "fk_location_id")
    private Location location;

    @Column(name = "trip_advisor_link")
    private String tripAdvisorLink;

    @ManyToOne
    @JoinColumn(name = "fk_type_id")
    private PartnerType type;

    @Column
    private String country;

    @OneToMany(mappedBy = "partner")
    @JsonManagedReference
    private Set<HotWord> hotWords = new HashSet<>();

    public Partner() {}

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTripAdvisorLink() {
        return tripAdvisorLink;
    }

    public void setTripAdvisorLink(String tripAdvisorLink) {
        this.tripAdvisorLink = tripAdvisorLink;
    }

    public PartnerType getType() {
        return type;
    }

    public void setType(PartnerType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<HotWord> getHotWords() {
        return hotWords;
    }

    public void setHotWords(Set<HotWord> hotWords) {
        this.hotWords = hotWords;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", horary='" + horary + '\'' +
                ", comment='" + comment + '\'' +
                ", specialOffer=" + specialOffer +
                ", offer='" + offer + '\'' +
                ", location=" + location +
                ", tripAdvisorLink='" + tripAdvisorLink + '\'' +
                ", type=" + type +
                ", country='" + country + '\'' +
                ", hotWords=" + hotWords +
                '}';
    }
}