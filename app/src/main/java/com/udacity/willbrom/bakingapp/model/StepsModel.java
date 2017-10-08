package com.udacity.willbrom.bakingapp.model;


import java.io.Serializable;

/**
 * class used by Gson library
 * to map 'steps JSON array'
 * */

public class StepsModel implements Serializable {
    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
