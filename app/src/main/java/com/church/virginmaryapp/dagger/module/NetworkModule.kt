package com.church.virginmaryapp.dagger.module

import com.church.virginmaryapp.api.BookingAPI
import com.church.virginmaryapp.api.CancelAPI
import com.church.virginmaryapp.api.CategoryAPI
import com.church.virginmaryapp.api.FollowReservation
import com.church.virginmaryapp.booking.bookEvent.BookingInteractor
import com.church.virginmaryapp.booking.showEvents.EventsInteractor
import com.church.virginmaryapp.followReservations.deleteEvent.CancelingInteractor
import com.church.virginmaryapp.followReservations.showFollowEvent.FollowReservationInteractor
import com.church.virginmaryapp.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class NetworkModule
{
    private var okHttpClient: OkHttpClient? = null
    private var retrofitInstance: Retrofit? = null

    private var eventsApi : CategoryAPI ?= null
    private var bookingApi : BookingAPI ?= null

    private var followReservation:FollowReservation ?= null
    private var deletedObj:CancelAPI ? = null


    //----------------------------- Basic Providers ----------------------

    @Provides
    @Singleton
    fun provideOkayHttpClient(): OkHttpClient {
        if (okHttpClient == null) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            okHttpClient = OkHttpClient.Builder()
                    .readTimeout(70, TimeUnit.SECONDS)
                    .connectTimeout(70, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()
        }
        return okHttpClient!!
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        if (retrofitInstance == null) {
            val retrofit = Retrofit.Builder()
                    .client(provideOkayHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            retrofitInstance = retrofit.build()
        }
        return retrofitInstance!!
    }

    //-------------------------------- api Providers ------------------------------

    @Provides
    @Singleton
    fun provideCategoryApi() : CategoryAPI
    {
        if(eventsApi == null)
            eventsApi = provideRetrofit().create(CategoryAPI::class.java)
        return eventsApi!!
    }

    @Provides
    @Singleton
    fun provideEventsInteractor() : EventsInteractor
    {
        return EventsInteractor(provideCategoryApi())
    }

    @Provides
    @Singleton
    fun provideBookingApi() : BookingAPI
    {
        if(bookingApi == null)
            bookingApi = provideRetrofit().create(BookingAPI::class.java)
        return bookingApi!!
    }

    @Provides
    @Singleton
    fun provideBookingInteractor() : BookingInteractor
    {
        return BookingInteractor(provideBookingApi())
    }


    //=============================================================================//

    @Provides
    @Singleton
    fun provideFollowApi() : FollowReservation
    {
        if(followReservation == null)
            followReservation = provideRetrofit().create(FollowReservation::class.java)
        return followReservation!!
    }

    @Provides
    @Singleton
    fun provideFollowInteractor() : FollowReservationInteractor
    {
        return FollowReservationInteractor(provideFollowApi())
    }

    @Provides
    @Singleton
    fun provideCancelApi() : CancelAPI
    {
        if(deletedObj == null)
            deletedObj = provideRetrofit().create(CancelAPI::class.java)
        return deletedObj!!
    }

    @Provides
    @Singleton
    fun provideCancellingInteractor() : CancelingInteractor
    {
        return CancelingInteractor(provideCancelApi())
    }
}

