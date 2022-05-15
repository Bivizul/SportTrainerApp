package com.bivizul.sporttrainerapp.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bivizul.sporttrainerapp.R
import com.bivizul.sporttrainerapp.presentation.viewmodels.UserViewModel
import com.bivizul.sporttrainerapp.utils.Constants
import com.bivizul.sporttrainerapp.utils.Constants.SPLASH_TIME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var viewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUserInfo()
        view.postDelayed({
            viewModel.user.observe(viewLifecycleOwner) {
                Log.d(Constants.TAG, "UserSplash: $it")
                if (it == null) {
                    findNavController().navigate(R.id.action_splashFragment_to_personalDataFragment)
                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                }
            }
        }, SPLASH_TIME)
    }
}