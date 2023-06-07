package com.example.quizbee.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizBee {
    @SerializedName("_id")
    private String id;

    @SerializedName("module")
    public Modules modules;

    @SerializedName("questions")
    public ArrayList<Questions> questions;
}
