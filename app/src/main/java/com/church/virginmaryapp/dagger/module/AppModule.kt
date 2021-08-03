package com.church.virginmaryapp.dagger.module

import android.app.Application
import com.church.virginmaryapp.MyFreeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Marina on 29/6/2019.
 */


@Module
class AppModule(private var myAppObject: MyFreeApplication) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return myAppObject
    }
}