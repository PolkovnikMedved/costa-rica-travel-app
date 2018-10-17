package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.Device;

/**
 * Author: Solodoukhin Viktor
 * Date: 17.10.18
 * Description: Device entity for API Methods
 */
public class DeviceDTO {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Token")
    private String token;

    public DeviceDTO(Device device) {
        this.id = device.getId();
        this.token = device.getToken();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
