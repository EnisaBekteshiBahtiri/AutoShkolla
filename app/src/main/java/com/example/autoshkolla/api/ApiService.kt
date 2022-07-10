package com.example.autoshkolla.api

import com.example.autoshkolla.model.CategoryModel
import com.example.autoshkolla.model.SignModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("categoryA")
    fun getCategoryAData(): Call<MutableList<CategoryModel>>

    @GET("categoryB")
    fun getCategoryBData(): Call<MutableList<CategoryModel>>

    @GET("categoryC")
    fun getCategoryCData(): Call<MutableList<CategoryModel>>

    @GET("categoryD")
    fun getCategoryDData(): Call<MutableList<CategoryModel>>

    @GET("images")
    fun getCategoryASigns(): Call<MutableList<SignModel>>

    @GET("images")
    fun getCategoryBSigns(): Call<MutableList<SignModel>>
}