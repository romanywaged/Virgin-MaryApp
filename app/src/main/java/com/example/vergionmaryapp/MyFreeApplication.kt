package com.example.vergionmaryapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.vergionmaryapp.dagger.component.DaggerNetworkComponent
import com.example.vergionmaryapp.dagger.component.NetworkComponent
import com.example.vergionmaryapp.dagger.module.AppModule
import com.example.vergionmaryapp.dagger.module.NetworkModule

@Suppress("DEPRECATION")
class MyFreeApplication : Application()
{
    var networkComponent: NetworkComponent? = null

    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}