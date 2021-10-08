package com.church.virginmaryapp.models.follow

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FollowEventModule : Serializable{

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("eventDayId")
    @Expose
    var eventDayId: Int? = null

    @SerializedName("noofTickets")
    @Expose
    var noofTickets: Int? = null

    @SerializedName("reservationCode")
    @Expose
    var reservationCode: String? = null

    @SerializedName("requesterNationalId")
    @Expose
    var requesterNationalId: String? = null

    @SerializedName("eventDayDto")
    @Expose
    var eventDayDto: EventDayDto? = null

    @SerializedName("eventReservationTicketResources")
    var eventReservationTicketResources: List<String>? = null
}