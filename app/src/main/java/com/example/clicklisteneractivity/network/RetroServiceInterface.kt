package com.example.idthometask.network


import android.util.Log
import com.example.clicklisteneractivity.model.StateListResponse
import retrofit2.Response

import retrofit2.http.GET

/***
 * Created by Pushkarev Andrey on 29 Oct 2021
 */

interface RetroServiceInterface {
    
    
    @GET("countytest/states")
    suspend fun getStates(): Response<StateListResponse>
    
//    @GET("countytest/states/{state}")
//    suspend fun getCountiesByState(@Path("state") state: String): CountyList
}