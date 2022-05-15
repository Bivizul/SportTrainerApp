package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentAnalyticsBinding
import com.bivizul.sporttrainerapp.presentation.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding: FragmentAnalyticsBinding
        get() = _binding ?: throw RuntimeException("FragmentAnalyticsBinding is null")

    private lateinit var viewModel: UserViewModel
    private var currentProgress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = binding.progressBar2
        progressBar.max = 25000

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUserInfo()

        viewModel.user.observe(viewLifecycleOwner) {
            binding.tvNumberProgress.text = it.progress.toString()
//            Log.d(TAG, "userAnalytics1: $it")
            when {
                it.progress in 25000..50000 -> binding.tvX.text = getString(R.string.one_x)
                it.progress in 50000..75000 -> binding.tvX.text = getString(R.string.two_x)
                it.progress in 75000..100000 -> binding.tvX.text = getString(R.string.three_x)
                it.progress in 100000..125000 -> binding.tvX.text = getString(R.string.four_x)
                it.progress >= 125000 -> binding.tvX.text = getString(R.string.five_x)
                else -> binding.tvX.text = getString(R.string.zero_x)
            }
            currentProgress = it.progress
            progressBar.progress = currentProgress
        }

        with(binding) {
            etDistance.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    etDistance.error = getString(R.string.wrong_distance)
                    btContribute.isEnabled = false
                }
                if (etDistance.text.isNotEmpty() && etSquats.text.isNotEmpty()) {
                    btContribute.isEnabled = true
                }
            }
            etSquats.doOnTextChanged { text, start, before, count ->
                if (text!!.isEmpty()) {
                    etSquats.error = getString(R.string.wrong_amount)
                    btContribute.isEnabled = false
                }
                if (etDistance.text.isNotEmpty() && etSquats.text.isNotEmpty()) {
                    btContribute.isEnabled = true
                }
            }

            btContribute.setOnClickListener {
                viewModel.user.observe(viewLifecycleOwner) {
                    it.distance = etDistance.text.toString().toInt() * 1000
                    it.squats = etSquats.text.toString().toInt()
                    it.progress = viewModel.calculation(it.distance, it.squats, it.weight)
                    it.date = SimpleDateFormat("dd.MM.yyyy").format(Date())
                    viewModel.setPersonalData(it)
//                    Log.d(TAG, "userAnalytics2: $it")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AnalyticsFragment()
    }
}