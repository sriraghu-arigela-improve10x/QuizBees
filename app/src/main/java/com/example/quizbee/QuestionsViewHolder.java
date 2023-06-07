package com.example.quizbee;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizbee.databinding.QuestionsItemBinding;
import com.example.quizbee.databinding.QuestionsItemBinding;

public class QuestionsViewHolder extends RecyclerView.ViewHolder {
    QuestionsItemBinding questionsItemBinding;
    public QuestionsViewHolder(QuestionsItemBinding questionsItemBinding) {
        super(questionsItemBinding.getRoot());
        this.questionsItemBinding = questionsItemBinding;
    }
}
