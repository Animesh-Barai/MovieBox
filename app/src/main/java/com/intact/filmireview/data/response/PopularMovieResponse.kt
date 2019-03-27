package com.intact.filmireview.data.response

import com.google.gson.annotations.SerializedName
import com.intact.filmireview.ui.model.PopularMovieDTO

/**
 *  created by Anurag on 19-Mar-2019
 *
 *  @Expose is used to allow or disallow serialization and deserialization.
 *  @Expose is optional and it has two configuration parameters: serialize
 *  and deserialize. By default they're set to true.
 */

data class PopularMovieResponse(
    @SerializedName("page") val pageNumber: String,
    @SerializedName("total_pages") val totalPages: String,
    @SerializedName("results") val popularMovies: ArrayList<PopularMovieDTO>
)