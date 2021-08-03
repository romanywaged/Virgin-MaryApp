package com.church.virginmaryapp.api

import com.church.virginmaryapp.models.booking.EventsResponse
import com.church.virginmaryapp.utils.GET_EVENTS_BY_CATEGORY_ID
import io.reactivex.Observable
import retrofit2.http.*

interface CategoryAPI
{
    @GET(GET_EVENTS_BY_CATEGORY_ID)
    fun getEventsByCategoryID(@Path("eventCategoryId") categoryId : Int)
            : Observable<EventsResponse>
}