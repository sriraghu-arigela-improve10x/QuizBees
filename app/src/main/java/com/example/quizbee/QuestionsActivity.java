package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quizbee.databinding.ActivityQuestionsBinding;
import com.example.quizbee.model.QuizBee;
import com.example.quizbee.network.QuizApi;
import com.example.quizbee.network.QuizApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {
    private ActivityQuestionsBinding binding;
    private ArrayList<QuizBee> quizBees = new ArrayList<>();
    private QuestionsAdapter questionsAdapter;
    private QuizApiService quizApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("QuizBee");
        setupApi();
        fetchQuizBeeDetails();
        setupAdapter();
        setupRV();
    }

    private void setupApi() {
        QuizApi quizApi = new QuizApi();
        quizApiService = quizApi.createQuizApiService();
    }

    private void fetchQuizBeeDetails() {
        Call<List<QuizBee>> call = quizApiService.fetchQuizBeeItems();
        call.enqueue(new Callback<List<QuizBee>>() {
            @Override
            public void onResponse(Call<List<QuizBee>> call, Response<List<QuizBee>> response) {
                List<QuizBee> quizBeeList = response.body();
                questionsAdapter.setData(quizBeeList);
                //Toast.makeText(QuestionsActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<QuizBee>> call, Throwable t) {
                Toast.makeText(QuestionsActivity.this, "Failed Load the Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupAdapter() {
        questionsAdapter = new QuestionsAdapter();
        questionsAdapter.setData(quizBees);
    }

    private void setupRV() {
        binding.quizbeeRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.quizbeeRv.setAdapter(questionsAdapter);
    }
}