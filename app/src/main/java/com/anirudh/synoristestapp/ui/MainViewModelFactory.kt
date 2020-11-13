package com.anirudh.synoristestapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory (
    private val  repository: MainRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        //will create our view model
        return MainViewModel(repository) as T
    }
}