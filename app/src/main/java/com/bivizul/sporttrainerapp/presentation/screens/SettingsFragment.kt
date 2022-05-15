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
import com.bivizul.sporttrainerapp.databinding.FragmentSettingsBinding
import com.bivizul.sporttrainerapp.presentation.viewmodels.QuestionViewModel
import com.bivizul.sporttrainerapp.presentation.viewmodels.UserViewModel
import com.bivizul.sporttrainerapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding ?: throw RuntimeException("FragmentSettingsBinding is null")

    private lateinit var userViewModel: UserViewModel
    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getUserInfo()

        questionViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]
        questionViewModel.getAnswerRoom()

        binding.btClearData.setOnClickListener {
            userViewModel.user.observe(viewLifecycleOwner) {
                userViewModel.deleteUser(it.name)
            }
            questionViewModel.answerList.observe(viewLifecycleOwner) {
                for (element in it) {
                    questionViewModel.deleteAnswerRoom(element.response)
                }
                Log.d(TAG, "List answers setting: $it")
            }
            findNavController().navigate(R.id.action_settingsFragment_to_personalDataFragment)
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}