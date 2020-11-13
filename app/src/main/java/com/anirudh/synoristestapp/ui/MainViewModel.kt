package com.anirudh.synoristestapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anirudh.synoristestapp.data.db.entities.LocalDocData
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    val mainList: LiveData<List<LocalDocData>> = MutableLiveData()
    var mainListener: MainListener? = null

    init {
        getAllOrderList()
    }

    fun getAllOrderList(){
        mainListener?.onStarted()
        mainRepository.getSavedDoc(object :MainRepository.OnData{
            override fun onSuccess(itemList: List<LocalDocData>) {
                //mainListener?.onSuccess()
                viewModelScope.launch {
                    mainList as MutableLiveData
                    mainList.value = itemList
                }
            }

            override fun onFailure(msg: String) {
                mainListener?.onFailure(msg)
            }

        })
    }
}