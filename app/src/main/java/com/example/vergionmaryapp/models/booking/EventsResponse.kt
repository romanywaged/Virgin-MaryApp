package com.example.vergionmaryapp.models.booking

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

    @SerializedName("response")
    @Expose
    var response: List<EventModule>? = null
}