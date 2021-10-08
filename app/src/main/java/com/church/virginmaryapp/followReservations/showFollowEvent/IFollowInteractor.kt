package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.models.booking.EventsResponse
import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.models.follow.FollowEventsResponse
import io.reactivex.Observable

interface IFollowInteractor {
    fun getFollowEventsList(Id: String) : Observable<FollowEventsResponse>

}