package com.church.virginmaryapp.booking.bookEvent

import com.church.virginmaryapp.models.RequestBookingBody
import com.church.virginmaryapp.models.booking.BookingResponseModule
import io.reactivex.Observable

interface IBookingInteractor
{
    fun submitBookingObject(bookingObject : RequestBookingBody): Observable<BookingResponseModule>
}