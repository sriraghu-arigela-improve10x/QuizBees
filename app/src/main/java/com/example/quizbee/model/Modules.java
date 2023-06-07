package com.example.quizbee.model;

import com.google.gson.annotations.SerializedName;

public class Modules {
    @SerializedName("correct_answer")
    private Integer correctAnswer;
    private Integer number;
    private String name;
    private Integer questions;
    private String revision;
}
