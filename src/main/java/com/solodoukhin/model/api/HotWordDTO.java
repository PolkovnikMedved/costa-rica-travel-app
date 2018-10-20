package com.solodoukhin.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solodoukhin.model.HotWord;

/**
 * Author: Solodoukhin Viktor
 * Date: 20.10.18
 * Description: Hot word model for api methods
 */
public class HotWordDTO {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    public HotWordDTO(HotWord hotWord) {
        this.id = hotWord.getId();
        this.name = hotWord.getName();
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
