package com.bivizul.sporttrainerapp.data.network

import com.bivizul.sporttrainerapp.domain.answer.Question
import com.bivizul.sporttrainerapp.domain.workout.WorkoutInfo
import com.bivizul.sporttrainerapp.domain.answer.Answer
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*


interface ApiService {

    @GET("/ios/{dayOfWeek}.json")
    suspend fun getWorkoutInfo(@Path("dayOfWeek") dayOfWeek: String): Response<List<WorkoutInfo>>

    @GET("/ios/response.php")
    suspend fun getAnswer(): Response<Answer>

    @POST("/ios/ask.php")
    suspend fun postQuestion(@Body question: Question): Response<Unit>

//
//    @PATCH("alerts/{alert_id}/accept")
//    fun accept_invited_alerts(@Header("X-Api-Token")  api_token: String, @Path("alert_id") alert_id: Int): Call<Unit>
//
//    @PATCH("alerts/{alert_id}/accept")
//    fun accept_invited_alerts2(@Header("X-Api-Token") api_token: String, @Path("alert_id") alert_id: Int): Observable<Response<Unit>>


//    @POST("/ios/ask.php")
//    suspend fun postQuestion(@Body question: Question)

//    @POST("/v1/registration")
//    fun registerUser(@Body registrationBody: RegistrationBody?): RegistrationResponse?

//    @GET("/user/{name}")
//    fun encoded(@Path("name") name: String?): Call<ResponseBody?>?



}