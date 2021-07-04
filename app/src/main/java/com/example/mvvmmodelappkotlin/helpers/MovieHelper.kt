package com.example.mvvmmodelappkotlin.helpers

import androidx.lifecycle.MutableLiveData
import com.example.mvvmmodelappkotlin.fragstates.MovieFragState
import com.example.mvvmmodelappkotlin.managers.ViewModelManager
import com.example.mvvmmodelappkotlin.models.MovieModel
import com.example.mvvmmodelappkotlin.others.Constants
import com.example.mvvmmodelappkotlin.retrofit.GetDataService
import com.example.mvvmmodelappkotlin.retrofit.RetrofitClientInstance
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MovieHelper {

    val movieFragStateMutableLiveData: MutableLiveData<MovieFragState> =
        MutableLiveData<MovieFragState>()

    val movies: Unit
        get() {
            val apiService: GetDataService = RetrofitClientInstance.retrofitInstance!!.create(
                GetDataService::class.java
            )
            apiService.getAllMovies("/3/search/movie?/&query=movie&api_key=" + Constants.API_KEY + "&language=en-US&")
                ?.enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        try {
                            if (response.isSuccessful) {
                                if (response.body() != null) {
                                    val movieModel: MovieModel = Gson().fromJson(
                                        response.body()!!.string(),
                                        MovieModel::class.java
                                    )
                                    ViewModelManager.instance.movieViewModel?.movieModels =
                                        movieModel

                                    postPositiveResponse(Constants.MOVIES)
                                }
                            } else {
                                postNegativeResponse(Constants.RESPONSE_ERROR, Constants.API_ERROR)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        postNegativeResponse(Constants.RESPONSE_ERROR, Constants.API_ERROR)
                    }
                })
        }

    fun postPositiveResponse(state: String?) {
        val fragState = MovieFragState()
        fragState.state = state
        fragState.isOk = true
        fragState.showWait = false
        movieFragStateMutableLiveData.postValue(fragState)
    }

    fun postNegativeResponse(errorCode: Int, errorMsg: String?) {
        val fragState = MovieFragState()
        fragState.state = Constants.API_ERROR
        fragState.isOk = false
        fragState.stateErrorCode = errorCode
        fragState.errorMsg = errorMsg
        fragState.showWait = false
        movieFragStateMutableLiveData.postValue(fragState)
    }

}
