package com.anirudh.synoristestapp.ui

interface MainListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}