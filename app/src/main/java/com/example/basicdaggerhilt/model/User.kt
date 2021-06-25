package com.example.basicdaggerhilt.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("age")
    val age: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String
)