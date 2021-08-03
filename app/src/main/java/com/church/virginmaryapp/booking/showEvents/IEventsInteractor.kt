package com.church.virginmaryapp.booking.showEvents


import com.church.virginmaryapp.models.booking.EventsResponse
import io.reactivex.Observable

interface IEventsInteractor
{
    fun getEventsList(categoryId: Int) : Observable<EventsResponse>
}