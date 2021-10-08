package com.church.virginmaryapp.api

import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.utils.EVENT_DELETE
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.HTTP

interface CancelAPI {
    @HTTP(method = "DELETE",path = EVENT_DELETE, hasBody = true)
    fun submitCancelingRequest(@Body requestBody: FollowEventModule)
            : Observable<CancelingResponseModule>
}