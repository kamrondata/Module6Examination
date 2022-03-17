package com.example.cardpro.networking

import com.example.cardpro.model.Card
import retrofit2.Call
import retrofit2.http.*

interface CardService {

    @GET("cards")
    fun getAllCards(): Call<ArrayList<Card>>

    @POST("cards")
    fun createCard(@Body card: Card): Call<Card>
}