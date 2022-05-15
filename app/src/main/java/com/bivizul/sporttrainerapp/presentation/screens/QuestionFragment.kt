package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentQuestionBinding
import com.bivizul.sporttrainerapp.domain.answer.Question
import com.bivizul.sporttrainerapp.presentation.AnswerAdapter
import com.bivizul.sporttrainerapp.presentation.viewmodels.QuestionViewModel
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding: FragmentQuestionBinding
        get() = _binding ?: throw RuntimeException("FragmentQuestionBinding is null")

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQuestionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AnswerAdapter()
        val question = Question()

        binding.rvQuestion.adapter = adapter
        viewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        viewModel.getAnswerRoom()
        viewModel.answerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d(TAG, "listAnswerFragment: $it")

        }

        binding.btPlay.setOnClickListener {
            question.ask = binding.etQuestion.text.toString()
            question.id++
            Log.d(TAG, "question: $question")
            view.postDelayed({
//                viewModel.postQuestion(question)
                viewModel.getAnswer()
            }, 1000)
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_mainFragment2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = QuestionFragment()
    }
}