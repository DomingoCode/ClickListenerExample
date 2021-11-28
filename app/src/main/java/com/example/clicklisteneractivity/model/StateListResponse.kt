package com.example.clicklisteneractivity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/***
 * List of States
 *
 * Created by Pushkarev Andrey on 27 Oct 2021
 */

@Serializable
data class StateListResponse(
    @SerialName("data")
    val data: ArrayList<State>
) : java.io.Serializable

data class State(
    @SerialName("state")
    val state: String,
    @SerialName("population")
    val population: Int,
    @SerialName("counties")
    val counties: Int,
    @SerialName("detail")
    val detail: String
) : java.io.Serializable
