package com.bivizul.sporttrainerapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.bivizul.sporttrainerapp.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

}