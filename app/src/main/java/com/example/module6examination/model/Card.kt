package com.example.cardpro.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey
    val id: String,

    val cardNumber: String? = null,
    val expireDate: String? = null,
    val holderName: String? = null,
    val cvv: Int? = null
)

