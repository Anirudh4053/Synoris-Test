package com.anirudh.synoristestapp.data.others

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val BASE_URL = "http://api.plos.org"
fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
fun View.show(){
    this.visibility = View.VISIBLE
}
fun View.hide(){
    this.visibility = View.GONE
}
fun <T> Call<T>.makeRequest(onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful){
                response.body()?.let { onSuccess(it) }
            }else{
                onFailure(Throwable("Some error occurred"))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            when(t){
                is UnknownHostException -> Throwable("Please check your internet connection.")
                is SocketTimeoutException -> Throwable("Very poor connection. Please check your internet.")
                else -> Throwable("Something went wrong")
            }
            onFailure(t)
        }
    })
}

@SuppressLint("SimpleDateFormat")
fun changeDateFormat(date:String):String {
    val input = SimpleDateFormat("yyyy-MM-dd")
    val output = SimpleDateFormat("dd MMM yyyy")
    return try {
        val oneWayTripDate = input.parse(date) // parse input
        output.format(oneWayTripDate) // format output
    } catch (e: ParseException) {
        e.printStackTrace()
        ""
    }
}