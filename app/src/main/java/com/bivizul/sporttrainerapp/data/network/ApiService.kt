package com.bivizul.sporttrainerapp.data.network

import com.bivizul.sporttrainerapp.domain.answer.Question
import com.bivizul.sporttrainerapp.domain.workout.WorkoutInfo
import com.bivizul.sporttrainerapp.domain.answer.Answer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @GET("/ios/{dayOfWeek}.json")
    suspend fun getWorkoutInfo(@Path("dayOfWeek") dayOfWeek: String): Response<List<WorkoutInfo>>

    @GET("/ios/response.php")
    suspend fun getAnswer(): Response<Answer>

    @POST("/ios/ask.php")
    fun postQuestion(@Body question: Question): Response<Answer>

//    @POST("/ios/ask.php")
//    suspend fun postQuestion(@Body question: Question)

//    @POST("/v1/registration")
//    fun registerUser(@Body registrationBody: RegistrationBody?): RegistrationResponse?

//    @GET("/user/{name}")
//    fun encoded(@Path("name") name: String?): Call<ResponseBody?>?

}