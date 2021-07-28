package com.example.vergionmaryapp.models.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EventModule : Serializable
{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("eventDate")
    @Expose
    var eventDate: String? = null

    @SerializedName("eventCode")
    @Expose
    var eventCode: String? = null

    @SerializedName("eventName")
    @Expose
    var eventName: String? = null

    @SerializedName("noofAvailableTickets")
    @Expose
    var noOfAvailableTickets: Int? = null

    @SerializedName("noofReservedTickets")
    @Expose
    var noOfReservedTickets: Int? = null
}