package com.church.virginmaryapp.booking.showEvents

import com.church.virginmaryapp.api.CategoryAPI
import com.church.virginmaryapp.models.booking.EventsResponse
import io.reactivex.Observable
import java.io.IOException


class EventsInteractor (private var categoryAPI : CategoryAPI) : IEventsInteractor
{
    override fun getEventsList(Id: Int): Observable<EventsResponse>
    {
        return Observable.defer {
            categoryAPI.getEventsByCategoryID(Id)
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