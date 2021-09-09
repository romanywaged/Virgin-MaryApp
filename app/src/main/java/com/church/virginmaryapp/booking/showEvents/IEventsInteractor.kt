package com.church.virginmaryapp.booking.showEvents


import com.church.virginmaryapp.models.booking.EventsResponse
import io.reactivex.Observable

interface IEventsInteractor
{
    fun getEventsList(Id: Int) : Observable<EventsResponse>
}