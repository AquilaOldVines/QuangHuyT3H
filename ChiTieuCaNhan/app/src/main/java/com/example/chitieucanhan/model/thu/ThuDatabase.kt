package com.example.chitieucanhan.model.thu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ThuData::class], version = 2)
abstract class ThuDatabase : RoomDatabase() {

    abstract fun thuDao() : ThuDao

    companion object {

        private var instanceThu : ThuDatabase? = null

        fun getDatabaseThu(context: Context): ThuDatabase {

            if (instanceThu == null){

                instanceThu = Room.databaseBuilder(context, ThuDatabase::class.java, "thu_database").build()
            }

            return instanceThu!!
        }
    }
}