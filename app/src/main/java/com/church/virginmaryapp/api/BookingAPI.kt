package com.church.virginmaryapp.api

import com.church.virginmaryapp.models.RequestBookingBody
import com.church.virginmaryapp.models.booking.BookingResponseModule
import com.church.virginmaryapp.utils.EVENT_RESERVATION
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface BookingAPI
{
    @POST(EVENT_RESERVATION)
    fun submitBookingRequest(@Body requestBody: RequestBookingBody)
            : Observable<BookingResponseModule>
}