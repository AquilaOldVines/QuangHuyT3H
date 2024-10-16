package com.example.chitieucanhan.viewmodel.thuviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chitieucanhan.model.thu.ThuData
import kotlinx.coroutines.launch

class ThuViewModel(application: Application) : ViewModel() {

    private val thuRepository : ThuRepository = ThuRepository(application)

    fun insertThu(thuData: ThuData) = viewModelScope.launch {

        thuRepository.insertThu(thuData)
    }

    fun updateThu(thuData: ThuData) = viewModelScope.launch {

        thuRepository.updateThu(thuData)
    }

    fun deleteThu(thuData: ThuData) = viewModelScope.launch {

        thuRepository.deleteThu(thuData)
    }

    fun getAllThu() : LiveData<List<ThuData>> = thuRepository.getAllThus()

    class ThuViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ThuViewModel::class.java)){

                @Suppress("UNCHECKD_CAST")
                return ThuViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}