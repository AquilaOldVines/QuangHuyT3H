package com.example.chitieucanhan.viewmodel.userviewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.chitieucanhan.model.user.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class UserViewModel(application: Application) : ViewModel() {

    private val userRepository : UserRepository = UserRepository(application)

    fun insertUser(userData: UserData) = viewModelScope.launch {

        userRepository.insertUser(userData)
    }

    fun updateUser(userData: UserData) = viewModelScope.launch {

        userRepository.updateUser(userData)
    }

    fun deleteUser(userData: UserData) = viewModelScope.launch {

        userRepository.deleteUser(userData)
    }

    fun getAllUser() : LiveData<List<UserData>> = userRepository.getAllUsers()

    fun getAllDateUser(date : Date) : LiveData<List<UserData>> = userRepository.getAllDateUser(date)

    fun getAllNameUser(name : String) : LiveData<List<UserData>> = userRepository.getAllNameUser(name)

    class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(UserViewModel::class.java)){

                @Suppress("UNCHECKD_CAST")
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}