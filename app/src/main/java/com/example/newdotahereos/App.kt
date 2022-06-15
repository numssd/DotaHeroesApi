package com.example.newdotahereos

import android.app.Application
import com.example.newdotahereos.data.CompositeRepository
import com.example.newdotahereos.data.FileRepository
import com.example.newdotahereos.data.NetworkRepository
import com.example.newdotahereos.data.model.HeroesItem
import com.example.newdotahereos.ui.Repository
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.OkHttpClient

@ExperimentalStdlibApi
class App : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val okHttpClient = OkHttpClient()
        val moshi = Moshi.Builder().build()
        val mapAdapter = moshi.adapter<Map<String, HeroesItem>>()
        val networkRepository = NetworkRepository(okHttpClient, mapAdapter)
        val fileRepository = FileRepository(this, mapAdapter)
        repository = CompositeRepository(networkRepository, fileRepository)
    }
}