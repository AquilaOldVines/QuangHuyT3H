package com.example.chitieucanhan.model.chi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chitieucanhan.model.thu.ThuDatabase

@Database(entities = [ChiData::class], version = 2)
abstract class ChiDatabase : RoomDatabase() {

    abstract fun chiDao() : ChiDao

    companion object {

        private var instanceChi : ChiDatabase? = null

        fun getDatabaseChi(context: Context): ChiDatabase {

            if (instanceChi == null){

                instanceChi = Room.databaseBuilder(context, ChiDatabase::class.java, "chi_database").build()
            }

            return instanceChi!!
        }
    }
}