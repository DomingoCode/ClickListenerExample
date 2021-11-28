package com.example.idthometask.network


import android.util.Log
import com.example.clicklisteneractivity.model.StateListResponse
import retrofit2.Response

import javax.inject.Inject
import kotlin.math.log

/***
 * Created by Pushkarev Andrey on 29 Oct 2021
 */

class RetroRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface) {
    
    suspend fun getStates(): Response<StateListResponse> {
        Log.e("andrey", "RetroRepository retroServiceInterface = $retroServiceInterface")
        return retroServiceInterface.getStates()
    }
    
    //suspend fun getCountiesByState(stateName: String): CountyList = retroServiceInterface.getCountiesByState(stateName)
    
    
}