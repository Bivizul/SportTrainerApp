package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentMainBinding
import com.bivizul.sporttrainerapp.presentation.viewmodels.UserViewModel
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataToday = SimpleDateFormat("dd.MM.yyyy").format(Date())

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUserInfo()
        viewModel.user.observe(viewLifecycleOwner) {
            Log.d(TAG, "userMain: $it")
            if(dataToday != it.date){
                it.progress = 0
            }
            viewModel.setPersonalData(it)
            binding.tvNumberProgress.text = it.progress.toString()
        }

        binding.btWorkout.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_workoutFragment)
        }
        binding.btAnalytics.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_analyticsFragment)
        }
        binding.btQuestion.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_questionFragment)
        }
        binding.btSettings.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
        }

    }

    companion object {
        fun newInstance() = MainFragment()
    }

}