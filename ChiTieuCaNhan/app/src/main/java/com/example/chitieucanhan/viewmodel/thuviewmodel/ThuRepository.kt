package com.example.chitieucanhan.viewmodel.thuviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.chitieucanhan.model.thu.ThuDao
import com.example.chitieucanhan.model.thu.ThuData
import com.example.chitieucanhan.model.thu.ThuDatabase

class ThuRepository(app : Application) {

    private val thuDao : ThuDao

    init {

        val thuDatabase : ThuDatabase = ThuDatabase.getDatabaseThu(app)
        thuDao = thuDatabase.thuDao()
    }

    suspend fun insertThu(thuData: ThuData) = thuDao.insert(thuData)
    suspend fun updateThu(thuData: ThuData) = thuDao.update(thuData)
    suspend fun deleteThu(thuData: ThuData) = thuDao.delete(thuData)

    fun getAllThus() : LiveData<List<ThuData>> = thuDao.getAll()
}