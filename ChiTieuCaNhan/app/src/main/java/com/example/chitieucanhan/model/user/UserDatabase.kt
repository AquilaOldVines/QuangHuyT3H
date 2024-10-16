package com.example.chitieucanhan.model.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UserData::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        private var instanceUser: UserDatabase? = null

        fun getDatabaseUser(context: Context): UserDatabase {

            if (instanceUser == null){

                instanceUser = Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
            }

            return instanceUser!!
        }
    }
}