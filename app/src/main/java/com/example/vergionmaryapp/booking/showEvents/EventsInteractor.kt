package com.example.vergionmaryapp.booking.showEvents

import com.example.vergionmaryapp.api.CategoryAPI
import com.example.vergionmaryapp.models.booking.EventsResponse
import io.reactivex.Observable
import java.io.IOException


class EventsInteractor (private var categoryAPI : CategoryAPI) : IEventsInteractor
{
    override fun getEventsList(categoryId: Int): Observable<EventsResponse>
    {
        return Observable.defer {
            categoryAPI.getEventsByCategoryID(categoryId)
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