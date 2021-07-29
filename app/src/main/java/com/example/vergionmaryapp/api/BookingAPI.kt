package com.example.vergionmaryapp.api

import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule
import com.example.vergionmaryapp.utils.EVENT_RESERVATION
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface BookingAPI
{
    @POST(EVENT_RESERVATION)
    fun submitBookingRequest(@Body requestBody: RequestBookingBody)
            : Observable<BookingResponseModule>
}