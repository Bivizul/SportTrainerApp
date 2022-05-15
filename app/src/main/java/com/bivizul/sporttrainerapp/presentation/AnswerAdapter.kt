package com.bivizul.sporttrainerapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bivizul.sporttrainerapp.databinding.ItemAnswerBinding
import com.bivizul.sporttrainerapp.domain.answer.Answer
import com.bivizul.sporttrainerapp.utils.Constants.RESPONSE_PROCESSED

class AnswerAdapter() : ListAdapter<Answer, AnswerHolder>(AnswerDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AnswerHolder {
        val binding = ItemAnswerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnswerHolder(binding)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            with(item) {
                tvQuestion.text = ask
                if (response != "") {
                    tvAnswer.text = response
                } else {
                    tvAnswer.text = RESPONSE_PROCESSED
                }
            }
        }
    }
}