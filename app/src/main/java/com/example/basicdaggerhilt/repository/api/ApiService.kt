package com.example.basicdaggerhilt.repository.api

import com.example.basicdaggerhilt.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("files/user.json")
    suspend fun getUser() : Response<User>
}