package com.church.virginmaryapp

import android.app.Application
import android.content.Context
import android.telecom.PhoneAccount.builder
import androidx.multidex.MultiDex
import com.church.virginmaryapp.dagger.component.DaggerNetworkComponent
import com.church.virginmaryapp.dagger.component.NetworkComponent
import com.church.virginmaryapp.dagger.module.AppModule
import com.church.virginmaryapp.dagger.module.NetworkModule

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