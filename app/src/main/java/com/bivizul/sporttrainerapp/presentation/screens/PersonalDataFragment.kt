package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.databinding.FragmentPersonalDataBinding
import com.bivizul.sporttrainerapp.domain.user.User
import com.bivizul.sporttrainerapp.presentation.viewmodels.UserViewModel
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PersonalDataFragment : Fragment() {

    private var _binding: FragmentPersonalDataBinding? = null
    private val binding: FragmentPersonalDataBinding
        get() = _binding ?: throw RuntimeException("FragmentPersonalDataBinding is null")

    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPersonalDataBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = User()

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUserInfo()
        viewModel.user.observe(viewLifecycleOwner) {
            Log.d(TAG, "UserPersonal: $it")
        }

        with(binding) {
            etName.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 2) {
                    etName.error = "Неверное имя"
                    btBegin.isEnabled = false
                }
                if (etName.text.isNotEmpty() && etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                    btBegin.isEnabled = true
                }
            }

            etHeight.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 2) {
                    etHeight.error = "Неверный рост"
                    btBegin.isEnabled = false
                }
                if (etName.text.isNotEmpty() && etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                    btBegin.isEnabled = true
                }
            }

            etWeight.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 2 || text.length > 3) {
                    etWeight.error = "Неверный вес"
                    btBegin.isEnabled = false
                }
                if (etName.text.isNotEmpty() && etHeight.text.isNotEmpty() && etWeight.text.isNotEmpty()) {
                    btBegin.isEnabled = true
                }
            }

            btBegin.setOnClickListener {
                userData.name = binding.etName.text.toString()
                userData.height = binding.etHeight.text.toString().toInt()
                userData.weight = binding.etWeight.text.toString().toInt()
                userData.date = SimpleDateFormat("dd.MM.yyyy").format(Date())
                viewModel.setPersonalData(userData)
                Log.d(TAG, "userData3: $userData")
                findNavController().navigate(R.id.action_personalDataFragment_to_mainFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PersonalDataFragment()
    }

}