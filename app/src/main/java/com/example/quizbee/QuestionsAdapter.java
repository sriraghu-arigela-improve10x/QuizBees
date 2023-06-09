package com.example.quizbee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.model.Questions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {

    public int currentQuestionPosition = 0;
    private List<Questions> questions;

    private OnItemActionListener onItemActionListener;

    void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    void setData(List<Questions> quizBees) {
        questions = quizBees;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsItemBinding questionsItemBinding = QuestionsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        QuestionsViewHolder questionsViewHolder = new QuestionsViewHolder(questionsItemBinding);
        return questionsViewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Questions questions = this.questions.get(position);
        holder.questionsItemBinding.quitionNumber.setText(String.valueOf(questions.getNumber()));
        holder.questionsItemBinding.getRoot().setOnClickListener(v -> {
            currentQuestionPosition = position;
            notifyDataSetChanged();
            onItemActionListener.onItemClicked((questions));
        });
        if (currentQuestionPosition == position) {
            holder.questionsItemBinding.quitionNumber.setTextColor(Color.parseColor("#FF5722"));
        } else {
            holder.questionsItemBinding.quitionNumber.setTextColor(Color.parseColor("#050505"));
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
