package com.example.vergionmaryapp.dagger.module

import android.app.Application
import com.example.vergionmaryapp.MyFreeApplication
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