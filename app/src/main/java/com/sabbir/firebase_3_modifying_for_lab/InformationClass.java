package com.sabbir.firebase_3_modifying_for_lab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sabbir on 017  17 05 17  May.
 */

//This class only sets the value... and gets the value
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationClass {
    private String year;
    private String description;

    //Alt+insert key is the hotkey for constructor generator

    public InformationClass() {
    }

    public String getYear() {
        return year;
    }

    public String setYear(String year) {
        this.year = year;
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
