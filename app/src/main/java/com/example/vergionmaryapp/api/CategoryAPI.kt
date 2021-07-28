package com.example.vergionmaryapp.api

import com.example.vergionmaryapp.models.booking.EventsResponse
import com.example.vergionmaryapp.utils.GET_EVENTS_BY_CATEGORY_ID
import io.reactivex.Observable
import retrofit2.http.*

interface CategoryAPI
{
    @GET(GET_EVENTS_BY_CATEGORY_ID)
    fun getEventsByCategoryID(@Path("eventCategoryId") categoryId : Int)
            : Observable<EventsResponse>
}