package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizbee.databinding.ActivityQuestionsBinding;
import com.example.quizbee.model.QuizBee;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {
    private ActivityQuestionsBinding binding;
    private ArrayList<QuizBee> quizBees = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("QuizBee");
        setupRV();
        setupAdapter();
    }

    private void setupAdapter() {
    }

    private void setupRV() {
    }
}