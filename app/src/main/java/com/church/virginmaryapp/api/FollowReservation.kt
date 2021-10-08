package com.church.virginmaryapp.api

import com.church.virginmaryapp.models.follow.FollowEventsResponse
import com.church.virginmaryapp.utils.FOLLOW_EVENT_RESRVATION
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface FollowReservation
{
    @GET(FOLLOW_EVENT_RESRVATION)
    fun followReservation(@Path("nationalId") nationalId:String)
            : Observable<FollowEventsResponse>
}