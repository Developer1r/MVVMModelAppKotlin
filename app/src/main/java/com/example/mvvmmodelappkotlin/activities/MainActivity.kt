package com.example.mvvmmodelappkotlin.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mvvmmodelappkotlin.R
import com.example.mvvmmodelappkotlin.fragstates.MovieFragState
import com.example.mvvmmodelappkotlin.managers.ViewModelManager
import com.example.mvvmmodelappkotlin.models.MovieModel
import com.example.mvvmmodelappkotlin.others.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var movieModel: MovieModel? = null
    private val mObserver: Observer<MovieFragState> =
        Observer<MovieFragState> { movieFragState: MovieFragState? ->
            if (movieFragState != null) {
                if (!movieFragState.isHasBeenViewed) {
                    if (movieFragState.isOk) {
                        when (movieFragState.state) {
                            Constants.MOVIES -> {
                                movieModel =
                                    ViewModelManager.instance.movieViewModel?.movieModels

                                setData()
                            }
                        }
                    } else {
                        when (movieFragState.stateErrorCode) {
                            Constants.RESPONSE_ERROR -> Toast.makeText(
                                this,
                                "Response Error",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    movieFragState.isHasBeenViewed = true
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    override fun onDestroy() {
        super.onDestroy()

        ViewModelManager.instance.movieViewModel?.helper?.movieFragStateMutableLiveData
            ?.removeObserver(mObserver)
    }

    private fun initUI() {
        ViewModelManager.instance.init(this)

        ViewModelManager.instance.movieViewModel?.helper?.movieFragStateMutableLiveData
            ?.observe(this, mObserver)

        ViewModelManager.instance.movieViewModel?.helper?.movies
    }

    private fun setData() {
        if (movieModel != null) {
            tv_title?.text = movieModel!!.results?.get(0)?.title
        }
    }

}
