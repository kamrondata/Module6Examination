package com.example.cardpro.activities

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cardpro.adapter.CardAdapter
import com.example.cardpro.database.CardRepository
import com.example.cardpro.model.Card
import com.example.cardpro.networking.RetrofitHttp
import com.example.module6examination.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var cards: ArrayList<Card>
    val TAG: String = MainActivity::class.java.simpleName
    lateinit var rvCards: RecyclerView
    lateinit var adapter: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rvCards = findViewById(R.id.rv_cards)
        rvCards.layoutManager = LinearLayoutManager(this)
        cards = ArrayList()
        getCard()

        if (isInternetAvailable()) {
            getCard()
        } else {
            getCardsFromDatabase()
        }
    }

    private fun refreshAdapter(cards: ArrayList<Card>) {
        adapter = CardAdapter(cards)
        rvCards.adapter = adapter
    }

    fun getCard() {
        RetrofitHttp.cardService.getAllCards().enqueue(object : Callback<ArrayList<Card>> {
            override fun onResponse(
                call: Call<ArrayList<Card>>,
                response: Response<ArrayList<Card>>
            ) {
                if (response.body() != null) {
                    cards.addAll(response.body()!!)
                    Log.d(TAG, "onREsponse: ${response.body()}")
                    saveToDatabase(response.body()!!)
                    refreshAdapter(getCardsFromDatabase())

                } else {
                    Log.d(TAG, "onResponse: null")
                }
            }

            override fun onFailure(call: Call<ArrayList<Card>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })

    }

    private fun saveToDatabase(cards: ArrayList<Card>) {
        val repository = CardRepository(application)
        for (card in cards)
            repository.saveCard(card)
    }

    private fun getCardsFromDatabase(): ArrayList<Card> {
        val repository = CardRepository(application)
        return repository.getCards() as ArrayList<Card>
    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        val infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return infoMobile!!.isConnected || infoWifi!!.isConnected
    }
}