package com.church.virginmaryapp.booking.bookEvent

import com.church.virginmaryapp.api.BookingAPI
import com.church.virginmaryapp.models.RequestBookingBody
import com.church.virginmaryapp.models.booking.BookingResponseModule
import io.reactivex.Observable
import java.io.IOException

class BookingInteractor (private var bookingAPI: BookingAPI) : IBookingInteractor
{
    override fun submitBookingObject(bookingObject : RequestBookingBody) : Observable<BookingResponseModule>
    {
        return Observable.defer {
            bookingAPI.submitBookingRequest(bookingObject)
                    .retryWhen { observable ->
                        observable.flatMap { throwable ->
                            if (throwable is IOException) {
                                Observable.error<Any>(Throwable("Please Check Your Internet Connection"))
                            }
                            Observable.error<Any>(throwable)
                        }
                    }
        }
    }

}