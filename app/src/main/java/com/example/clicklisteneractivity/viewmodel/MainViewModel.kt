package com.example.clicklisteneractivity.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clicklisteneractivity.model.StateListResponse
import com.example.idthometask.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetroRepository) : ViewModel() {
    
    private var _stateListResponseLiveData: MutableLiveData<StateListResponse> = MutableLiveData()
    val stateListResponseLiveData: LiveData<StateListResponse> = _stateListResponseLiveData
    
    var isLoading = ObservableBoolean(false)
    
    fun getStateList() {
        Log.e("andrey", "getStateList from viewModel")
        Log.e("andrey", "repository = $repository")
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getStates()
            if (response.isSuccessful) {
                _stateListResponseLiveData.postValue(response.body())
                isLoading.set(false)
            } else {
                Log.e("andrey", " response.message() = ${response.message()}")
                
            }
        }
    }
    
    
}