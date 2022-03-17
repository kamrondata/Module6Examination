package com.example.cardpro.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cardpro.model.Card
import com.example.ogabekroomretrofit.database.CardDao

@Database(entities = [Card::class], version = 2)
abstract class RoomManager : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object{
        private var instance: RoomManager? = null

        fun getDatabase(context: Context): RoomManager?{
            if(instance == null){
                synchronized(RoomManager::class.java){
                    instance = Room.databaseBuilder(context, RoomManager::class.java, "cards.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }
}