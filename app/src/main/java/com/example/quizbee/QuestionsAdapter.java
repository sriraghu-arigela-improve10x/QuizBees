package com.example.quizbee;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.model.Questions;
import com.example.quizbee.model.QuizBee;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {
    private List<QuizBee> quizBeeList;

    void setData(List<QuizBee> quizBees) {
        quizBeeList = quizBees;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding questionsItemBinding = QuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(questionsItemBinding);
        return questionsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        QuizBee quizBee = quizBeeList.get(position);
        holder.questionsItemBinding.quitionNumber.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return quizBeeList.size();
    }
}
