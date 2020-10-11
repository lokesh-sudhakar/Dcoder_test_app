package com.example.decoder_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
public class CodeResponse {

    @SerializedName("data")
    private List<CodeFiles> data = null;
    @SerializedName("pages")
    private Integer pages;

    public List<CodeFiles> getData() {
        return data;
    }

    public Integer getPages() {
        return pages;
    }
}