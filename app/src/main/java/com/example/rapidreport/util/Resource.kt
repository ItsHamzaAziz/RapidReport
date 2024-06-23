package com.example.rapidreport.util

// Type T meaning it can be of any type
// By default both data and string are null (if there is error then data is null and vice versa)
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)       // If success then return data
    class Error<T>(message: String?) : Resource<T>(message = message)   // If error then return error message
}