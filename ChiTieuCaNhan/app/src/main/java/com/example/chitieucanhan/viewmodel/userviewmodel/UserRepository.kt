package com.example.chitieucanhan.viewmodel.userviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.chitieucanhan.model.user.UserDao
import com.example.chitieucanhan.model.user.UserData
import com.example.chitieucanhan.model.user.UserDatabase
import java.util.Date

class UserRepository(app : Application) {

    private val userDao: UserDao

    init {

        val userDatabase : UserDatabase = UserDatabase.getDatabaseUser(app)
        userDao = userDatabase.userDao()
    }

    suspend fun insertUser(userData: UserData) = userDao.insert(userData)
    suspend fun updateUser(userData: UserData) = userDao.update(userData)
    suspend fun deleteUser(userData: UserData) = userDao.delete(userData)

    fun getAllUsers() : LiveData<List<UserData>> =  userDao.getAll()
    fun getAllDateUser(date : Date) : LiveData<List<UserData>> = userDao.getAllDate(date)
    fun getAllNameUser(name : String) : LiveData<List<UserData>> = userDao.getAllName(name)
}