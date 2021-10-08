package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.api.CancelAPI
import com.church.virginmaryapp.api.FollowReservation
import com.church.virginmaryapp.booking.showEvents.IEventsInteractor
import com.church.virginmaryapp.models.booking.EventsResponse
import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.models.follow.FollowEventsResponse
import io.reactivex.Observable
import java.io.IOException

class FollowReservationInteractor(private var followReservation: FollowReservation) : IFollowInteractor {
    override fun getFollowEventsList(Id: String): Observable<FollowEventsResponse> {
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