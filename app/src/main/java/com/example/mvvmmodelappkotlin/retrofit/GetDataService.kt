package com.example.mvvmmodelappkotlin.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface GetDataService {

    @GET
    fun getAllMovies(@Url url: String?): Call<ResponseBody?>?

}
