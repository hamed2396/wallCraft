package com.example.wallcraft.data.network

import com.example.wallcraft.data.models.detail.ResponseDetail
import com.example.wallcraft.data.models.explore.ResponsePhotosByOrder
import com.example.wallcraft.data.models.home.ResponseCategories
import com.example.wallcraft.data.models.home.ResponsePhotos
import com.example.wallcraft.data.models.search.ResponseSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiServices {
    @GET("topics")
    suspend fun getCategory(): Response<ResponseCategories>

    @GET("topics/{id}/photos")
    suspend fun getHomePhotos(
        @Path("id") id: String,
        @Query("page") page: Int
    ): Response<ResponsePhotos>

    @GET("photos/{id}")
    suspend fun getDetailPhoto(@Path("id") id: String): Response<ResponseDetail>

    @GET("search/photos")
    suspend fun getSearchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<ResponseSearch>


    @GET("photos")
    suspend fun getPhotosByOrder(
        @Query("order_by") order: String = "latest",
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 20
    ): Response<ResponsePhotosByOrder>
}