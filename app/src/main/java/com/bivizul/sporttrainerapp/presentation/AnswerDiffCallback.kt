package com.bivizul.sporttrainerapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.bivizul.sporttrainerapp.domain.answer.Answer

class AnswerDiffCallback : DiffUtil.ItemCallback<Answer>() {

    // сравниваем, один и тот же объект?
    override fun areItemsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem.response == newItem.response
    }

    // смотрим на изменение содержимого
    override fun areContentsTheSame(oldItem: Answer, newItem: Answer): Boolean {
        return oldItem == newItem
    }
}