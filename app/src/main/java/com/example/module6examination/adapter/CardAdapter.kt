package com.example.cardpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cardpro.model.Card
import com.example.module6examination.R

class CardAdapter(var cards: ArrayList<Card>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
       return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CardViewHolder){
            holder.cardHolder.text = cards[position].holderName
            holder.cardNumber.text = cards[position].cardNumber
            holder.cardExpireDate.text = cards[position].expireDate
            holder.cardCvv.text = cards[position].cvv.toString()
            holder.imageView.setBackgroundResource(R.drawable.background)
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class CardViewHolder(view: View): RecyclerView.ViewHolder(view){
        var cardHolder: TextView
        var cardNumber: TextView
        var cardExpireDate: TextView
        var cardCvv: TextView
        var imageView: ImageView

        init {
            cardHolder = view.findViewById(R.id.tv_card_holder)
            cardNumber = view.findViewById(R.id.card_number)
            cardExpireDate = view.findViewById(R.id.tv_expire_date)
            cardCvv = view.findViewById(R.id.card_cvv)
            imageView = view.findViewById(R.id.imageView)
        }

    }
}