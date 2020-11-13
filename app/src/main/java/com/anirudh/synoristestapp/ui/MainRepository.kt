package com.anirudh.synoristestapp.ui

import androidx.lifecycle.MutableLiveData
import com.anirudh.synoristestapp.data.db.AppDatabase
import com.anirudh.synoristestapp.data.db.entities.LocalDocData
import com.anirudh.synoristestapp.data.network.DocData
import com.anirudh.synoristestapp.data.network.MyApi
import com.anirudh.synoristestapp.data.others.makeRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainRepository(private val myApi: MyApi,private val db: AppDatabase) {
    interface OnData {
        fun onSuccess(itemList: List<LocalDocData>)
        fun onFailure(msg:String)
    }
    fun getSavedDoc(onData:OnData) {
        GlobalScope.launch {
            val locData = db.getDocDao().getDocList()
            if(locData.isNullOrEmpty()) {
                myApi.getDocList().makeRequest(
                        onSuccess = { data ->
                            println("NewDATA $data")
                            if(!data.response.docs.isNullOrEmpty()) {
                                GlobalScope.launch {
                                    val newArr = arrayListOf<LocalDocData>()
                                   for(i in data.response.docs) {
                                       newArr.add(LocalDocData(0,i.abstract.joinToString(),i.articleType,
                                           i.id,i.publicationDate,i.titleDisplay))

                                   }
                                    db.getDocDao().insertDocList(newArr)
                                    val locData1 = db.getDocDao().getDocList()
                                    onData.onSuccess(locData1)
                                }
                            } else {
                                onData.onFailure("No data found")
                            }
                        },
                        onFailure = { throwable ->
                            onData.onFailure(throwable.message ?: "Some error occurred")
                        })

            } else {
                onData.onSuccess(locData)
            }
        }
    }
}