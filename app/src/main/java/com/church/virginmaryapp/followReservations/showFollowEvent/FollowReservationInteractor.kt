package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.api.FollowReservation
import com.church.virginmaryapp.booking.showEvents.IEventsInteractor
import com.church.virginmaryapp.models.booking.EventsResponse
import io.reactivex.Observable
import java.io.IOException

class FollowReservationInteractor(private var followReservation: FollowReservation) : IFollowInteractor {
    override fun getFollowEventsList(Id: String): Observable<EventsResponse> {
        return Observable.defer {
            followReservation.followReservation(Id).retryWhen { observable ->
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