package com.example.hellohomeo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrewMembersModalClass {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("agency")
    @Expose
    private String agency;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("wikipedia")
    @Expose
    private String wikipedia;

    @SerializedName("status")
    @Expose
    private String status;

    public CrewMembersModalClass(String name, String agency, String image, String wikipedia, String status) {
        this.name = name;
        this.agency = agency;
        this.image = image;
        this.wikipedia = wikipedia;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getAgency() {
        return agency;
    }

    public String getImage() {
        return image;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getStatus() {
        return status;
    }
}
