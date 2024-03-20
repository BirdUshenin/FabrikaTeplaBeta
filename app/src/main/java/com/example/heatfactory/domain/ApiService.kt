package com.example.heatfactory.domain

import com.example.heatfactory.data.CategoryItem
import com.example.heatfactory.data.Item
import retrofit2.http.GET

interface ApiService {
    @GET("/HeatFactoryAPI/products.json")
    suspend fun getItems(): List<Item>

    @GET("/HeatFactoryAPI/boilers.json")
    suspend fun getBoilers(): List<CategoryItem>
}
