package com.example.interestingplaces;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Place {

    public String name;
    public String description;

    public Place() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }
}