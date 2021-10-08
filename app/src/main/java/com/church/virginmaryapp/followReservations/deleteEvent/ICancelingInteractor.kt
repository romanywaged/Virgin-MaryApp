package com.church.virginmaryapp.followReservations.deleteEvent

import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import io.reactivex.Observable

interface ICancelingInteractor {
    fun submitCancelingEventObject(cancelingObject : FollowEventModule): Observable<CancelingResponseModule>
}
