package com.example.quizbee;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.model.Questions;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsViewHolder> {
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

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        Questions questions = this.questions.get(position);
        holder.questionsItemBinding.quitionNumber.setText(String.valueOf(questions.getNumber()));
        holder.questionsItemBinding.getRoot().setOnClickListener(v -> {
            onItemActionListener.onItemClicked((questions));
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
