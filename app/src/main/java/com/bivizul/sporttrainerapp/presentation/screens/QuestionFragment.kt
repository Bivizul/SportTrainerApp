package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentQuestionBinding
import com.bivizul.sporttrainerapp.domain.answer.Answer
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
        val answer = Answer()
        val list = mutableListOf<Answer>()

        binding.rvQuestion.adapter = adapter
        viewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        viewModel.getAnswerRoom()


        binding.btPlay.setOnClickListener {
            question.ask = binding.etQuestion.text.toString()
//            question.id++
//            list.add(answer)
//            Log.d(TAG, "answer: $answer")
            view.postDelayed({
                viewModel.postQuestion(question)
                viewModel.getAnswer()
//                restartFragment(R.id.questionFragment)
//                viewModel.setAnswerRoom(answer)
            }, 1000)
            viewModel.getAnswerRoom()
        }

        viewModel.answerList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d(TAG, "listAnswerFragment: $it")
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_questionFragment_to_mainFragment2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    fun restartFragment(fragmentId: Int) {
//        val currentFragment =
//            activity?.supportFragmentManager?.findFragmentById(fragmentId)!!
//
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.detach(currentFragment)
//            ?.commit()
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.attach(currentFragment)
//            ?.commit()
//    }



    companion object {
        fun newInstance() = QuestionFragment()
    }
}