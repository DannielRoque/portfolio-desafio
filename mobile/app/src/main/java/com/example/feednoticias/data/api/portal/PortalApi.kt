package com.example.feednoticias.data.api.portal

import com.example.feednoticias.data.entity.PortalViewData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PortalApi {
    @GET("/feed/g1")
    fun getInitialFeed(): List<PortalViewData>

    @GET("feed/page/{product}/{page}")
    suspend fun getPaginatedFeed(
        @Path("product") product: String,
        @Path("page") page: Int): List<PortalViewData>

}