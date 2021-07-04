package com.example.mvvmmodelappkotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mvvmmodelappkotlin.helpers.MovieHelper
import com.example.mvvmmodelappkotlin.models.MovieModel

class MovieViewModel(application: Application?) : AndroidViewModel(application!!) {

    private var movieHelper: MovieHelper? = null
    var movieModels: MovieModel? = null

    val helper: MovieHelper?
        get() {
            if (movieHelper == null) {
                movieHelper = MovieHelper()
            }

            return movieHelper
        }

}
