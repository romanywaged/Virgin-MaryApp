package com.example.vergionmaryapp.booking.bookEvent

import com.example.vergionmaryapp.api.BookingAPI
import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule
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