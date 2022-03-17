package com.example.cardpro.database

import android.app.Application
import com.example.cardpro.model.Card
import com.example.ogabekroomretrofit.database.CardDao

class CardRepository(application: Application) {

    private val db = RoomManager.getDatabase(application)
    private val cardDao: CardDao = db!!.cardDao()

    fun getCards(): List<Card>{
        return cardDao.getCards()
    }

    fun saveCard(card: Card){
        cardDao.saveCard(card)
    }
}