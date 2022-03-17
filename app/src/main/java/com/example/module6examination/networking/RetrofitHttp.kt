package com.example.cardpro.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttp {

    companion object {

        const val SERVER_PRODUCTION = "https://6232cf7b6de3467dbac05abf.mockapi.io/"

        private fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_PRODUCTION)
                .build()
        }

        val cardService: CardService = getRetrofit().create(CardService::class.java)
    }
}