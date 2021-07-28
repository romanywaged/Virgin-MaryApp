package com.example.vergionmaryapp.booking.showEvents


import com.example.vergionmaryapp.models.booking.EventsResponse
import io.reactivex.Observable

interface IEventsInteractor
{
    fun getEventsList(categoryId: Int) : Observable<EventsResponse>
}