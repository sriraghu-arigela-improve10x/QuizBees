package com.example.quizbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizbee.databinding.ActivityQuestionsBinding;
import com.example.quizbee.model.Questions;
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
    private ArrayList<Questions> questions = new ArrayList<>();
    private QuestionsAdapter questionsAdapter;
    private QuizApiService quizApiService;

    private int currentQuestionNumber = 0;

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
        handleNextBtn();
        handleSubmitBtn();
        handlePreviousBtn();
    }

    private void handlePreviousBtn() {
        binding.previousBtn.setOnClickListener(v -> {
            try {
                currentQuestionNumber = questionsAdapter.currentQuestionPosition;
                currentQuestionNumber--;
                Questions question = questions.get(currentQuestionNumber);
                getQuestionsData(question);
                questionsAdapter.currentQuestionPosition = currentQuestionNumber;
                questionsAdapter.notifyDataSetChanged();
        } catch(Exception exception) {
                Toast.makeText(this, "There is no Questions", Toast.LENGTH_SHORT).show();
            }
            });
    }

    private void handleSubmitBtn() {
        binding.submitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        });
    }

    private void handleNextBtn() {
        binding.nextBtn.setOnClickListener(v -> {
            currentQuestionNumber = questionsAdapter.currentQuestionPosition;
            currentQuestionNumber ++;
            if (currentQuestionNumber == questions.size() - 1) {
                binding.nextBtn.setVisibility(View.GONE);
                binding.submitBtn.setVisibility(View.VISIBLE);
            } else {
                binding.nextBtn.setVisibility(View.VISIBLE);
                binding.submitBtn.setVisibility(View.GONE);
            }
            Questions question = questions.get(currentQuestionNumber);
            getQuestionsData(question);
            questionsAdapter.currentQuestionPosition = currentQuestionNumber;
            questionsAdapter.notifyDataSetChanged();
        });
    }

    private void getQuestionsData(Questions question) {
        binding.quitionTxt.setText(question.getQuestion());
        binding.radioCrOne.setText(question.getAnswers().get(0));
        binding.radioCrTwo.setText(question.getAnswers().get(1));
        binding.radioCrThree.setText(question.getAnswers().get(2));
        binding.radioCrFour.setText(question.getAnswers().get(3));
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
                questionsAdapter.setData(quizBeeList.get(0).getQuestions());
                questions = quizBeeList.get(0).getQuestions();
                getQuestionsData(questions.get(0));
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
        questionsAdapter.setData(questions);
        questionsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Questions questions) {
                getQuestionsData(questions);
            }
        });
    }

    private void setupRV() {
        binding.quizbeeRv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.quizbeeRv.setAdapter(questionsAdapter);
    }
}