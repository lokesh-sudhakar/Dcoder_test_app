package com.example.decoder_app.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.decoder_app.utils.DataTypeConverters;
import com.google.gson.annotations.SerializedName;

/**
 * @author Lokesh chennamchetty
 * @date 10/10/2020
 */
@Entity(tableName = "code_files_table")
public class CodeFiles {

    @SerializedName("_id")
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("is_project")
    private Boolean isProject;
    @SerializedName("language_id")
    private Integer languageId;
    @SerializedName("file")
    private String file;
    @SerializedName("stars")
    @TypeConverters(DataTypeConverters.class)
    private ValueObject stars;
    @SerializedName("username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsProject() {
        return isProject;
    }

    public void setIsProject(Boolean isProject) {
        this.isProject = isProject;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public ValueObject getStars() {
        return stars;
    }

    public void setStars(ValueObject stars) {
        this.stars = stars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}