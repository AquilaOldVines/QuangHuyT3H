package com.example.chitieucanhan.viewmodel.chiviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chitieucanhan.model.chi.ChiData
import kotlinx.coroutines.launch

class ChiViewModel(application: Application) : ViewModel() {

    private val chiRepository : ChiRepository = ChiRepository(application)

    fun insertChi(chiData: ChiData) = viewModelScope.launch {

        chiRepository.insertChi(chiData)
    }

    fun updateChi(chiData: ChiData) = viewModelScope.launch {

        chiRepository.updateChi(chiData)
    }

    fun deleteChi(chiData: ChiData) = viewModelScope.launch {

        chiRepository.deleteChi(chiData)
    }

    fun getAllChi() : LiveData<List<ChiData>> = chiRepository.getAllChis()

    class ChiViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ChiViewModel::class.java)){

                @Suppress("UNCHECKD_CAST")
                return ChiViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}