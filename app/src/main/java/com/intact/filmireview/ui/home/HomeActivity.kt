package com.intact.filmireview.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.intact.filmireview.R
import com.intact.filmireview.extension.observeLiveData
import com.intact.filmireview.ui.BaseActivity
import com.intact.filmireview.ui.CustomViewModelFactory
import com.intact.filmireview.ui.model.ErrorDTO
import com.intact.filmireview.ui.model.PopularMovieDTO
import timber.log.Timber
import javax.inject.Inject
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.AppCenter

class HomeActivity : BaseActivity() {

    @BindView(R.id.popularRecyclerView)
    lateinit var popularRecyclerView: RecyclerView

    @Inject
    lateinit var popularMoviesAdapter: PopularMoviesAdapter

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeVariables()
    }

    private fun initializeVariables() {

        // initialize butterknife
        ButterKnife.bind(this)

        // initializing app center for continous integration
        AppCenter.start(
            application, "5a8030df-7ef1-4007-a0f7-807d8d6dd058",
            Analytics::class.java, Crashes::class.java
        )

        // update empty UI
        updateUI()

        // get the view model
        val homeViewModel = ViewModelProviders.of(this@HomeActivity, viewModelFactory).get(HomeViewModel::class.java)
        setObservers(homeViewModel)
        homeViewModel.getPopularMovies()
    }

    private fun updateUI() {
        popularMoviesAdapter.setMoviesData(ArrayList<PopularMovieDTO>())

        with(popularRecyclerView) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesAdapter
            popularMoviesAdapter.notifyDataSetChanged()
        }
    }

    // setting the observers
    private fun setObservers(viewModel: HomeViewModel) {
        observeLiveData(viewModel.getPopularMoviesLiveData()) { updateMovieList(it) }
        observeLiveData(viewModel.getErrorLiveData()) { onError(it) }
    }

    // updating the popular movie list
    private fun updateMovieList(list: ArrayList<PopularMovieDTO>) {
        Timber.d("Updating movies data")
        popularMoviesAdapter.setMoviesData(list)
    }

    // on error received
    private fun onError(dto: ErrorDTO) {
        Timber.d("onError: $dto")
    }
}
