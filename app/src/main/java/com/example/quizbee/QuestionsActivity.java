package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quizbee.databinding.ActivityQuestionsBinding;

public class QuestionsActivity extends AppCompatActivity {
    private ActivityQuestionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("QuizBee");
    }
}