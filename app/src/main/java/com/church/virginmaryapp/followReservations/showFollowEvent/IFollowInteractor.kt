package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.models.booking.EventsResponse
import io.reactivex.Observable

interface IFollowInteractor {
    fun getFollowEventsList(Id: String) : Observable<EventsResponse>
}