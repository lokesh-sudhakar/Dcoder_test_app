package com.example.decoder_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Lokesh chennamchetty
 * @date 12/10/2020
 */
public class ValueObject {

    @SerializedName("number")
    @Expose
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}