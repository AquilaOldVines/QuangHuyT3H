package com.example.chitieucanhan.viewmodel.chiviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.chitieucanhan.model.chi.ChiDao
import com.example.chitieucanhan.model.chi.ChiData
import com.example.chitieucanhan.model.chi.ChiDatabase

class ChiRepository(app : Application) {

    private val chiDao: ChiDao

    init {

        val chiDatabase : ChiDatabase = ChiDatabase.getDatabaseChi(app)
        chiDao = chiDatabase.chiDao()
    }

    suspend fun insertChi(chiData: ChiData) = chiDao.insert(chiData)
    suspend fun updateChi(chiData: ChiData) = chiDao.update(chiData)
    suspend fun deleteChi(chiData: ChiData) = chiDao.delete(chiData)

    fun getAllChis() : LiveData<List<ChiData>> =  chiDao.getAll()
}