package com.example.vergionmaryapp.booking.bookEvent

import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule
import io.reactivex.Observable

interface IBookingInteractor
{
    fun submitBookingObject(bookingObject : RequestBookingBody): Observable<BookingResponseModule>
}