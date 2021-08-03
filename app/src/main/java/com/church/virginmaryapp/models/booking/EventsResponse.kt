package com.church.virginmaryapp.models.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EventsResponse : Serializable
{
    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("detailedMessage")
    @Expose
    var detailedMessage: String? = null

    @SerializedName("errors")
    @Expose
    var errorsObject: List<String>? = null

    @SerializedName("response")
    @Expose
    var eventsList: List<EventModule>? = null
}