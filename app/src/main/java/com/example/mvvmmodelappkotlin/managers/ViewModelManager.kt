package com.example.mvvmmodelappkotlin.managers

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmmodelappkotlin.viewmodels.MovieViewModel

class ViewModelManager {

    var movieViewModel: MovieViewModel? = null

    fun init(activity: FragmentActivity?) {
        movieViewModel = activity?.let { ViewModelProvider(it).get(MovieViewModel::class.java) }
    }

    companion object {
        val instance = ViewModelManager()
    }

}
