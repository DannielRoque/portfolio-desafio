package com.example.feednoticias.data.api.portal

import com.example.feednoticias.data.entity.PortalViewData
import retrofit2.http.GET

interface PortalApi {
    @GET("/feed/g1")
    fun getInitialFeed(): List<PortalViewData>
}