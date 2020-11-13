package com.anirudh.synoristestapp.data.network

import com.anirudh.synoristestapp.data.others.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @GET("/search?q=title:DNA")
    fun getDocList(): Call<ResponseData>

    companion object {
        operator fun invoke() : MyApi{
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder();
            httpClient.addInterceptor {
                val original: Request = it.request()
                var request: Request? = null
                request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .build()

                it.proceed(request)
            }
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(interceptor)
            val client = httpClient.build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()
                .create(MyApi::class.java)
        }
    }
}
