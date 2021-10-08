package com.church.virginmaryapp.followReservations.deleteEvent

import com.church.virginmaryapp.api.CancelAPI
import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import io.reactivex.Observable
import java.io.IOException

class CancelingInteractor(private var cancelApi: CancelAPI) : ICancelingInteractor {

    override fun submitCancelingEventObject(cancelingObject: FollowEventModule): Observable<CancelingResponseModule> {
        return Observable.defer {
            cancelApi.submitCancelingRequest(cancelingObject)
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