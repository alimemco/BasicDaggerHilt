package com.example.basicdaggerhilt.repository

import com.example.basicdaggerhilt.repository.api.ApiService
import javax.inject.Inject


class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getUser() = apiService.getUser()
}