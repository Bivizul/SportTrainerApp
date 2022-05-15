package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentWorkoutBinding
import com.bivizul.sporttrainerapp.presentation.viewmodels.WorkoutViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutFragment : Fragment() {

    private lateinit var viewModel: WorkoutViewModel

    private var _binding: FragmentWorkoutBinding? = null
    private val binding: FragmentWorkoutBinding
        get() = _binding ?: throw RuntimeException("FragmentWorkoutBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentWorkoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]
        viewModel.getWorkoutInfo()
        viewModel.workout.observe(viewLifecycleOwner) {
            with(binding) {
                tvDayWeek.text = viewModel.dayOfWeekRus
                Picasso.get().load(it[0].img).into(img1)
                tvDescription.text = it[0].text
            }
        }

        binding.btBack.setOnClickListener {
            findNavController().navigate(R.id.action_workoutFragment_to_mainFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = WorkoutFragment()
    }
}