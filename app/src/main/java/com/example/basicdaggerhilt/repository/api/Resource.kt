package com.example.basicdaggerhilt.repository.api

data class Resource<out T>(val requestStatus: RequestStatus, val data : T?, val message : String?)

{
    companion object{
        fun <T> success(data : T?) : Resource<T> {
            return Resource(RequestStatus.SUCCESS , data , null)
        }

        fun <T> error(message: String?) : Resource<T> {
            return Resource(RequestStatus.ERROR , null , message)
        }

        fun <T> loading() : Resource<T> {
            return Resource(RequestStatus.LOADING ,null , null)
        }
    }
}