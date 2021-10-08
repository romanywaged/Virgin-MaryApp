package com.church.virginmaryapp.followReservations.deleteEvent

import com.church.virginmaryapp.models.follow.EventDayDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestCancelingEvent : Serializable{

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
    var reservationCode: Int? = null

    @SerializedName("requesterNationalId")
    @Expose
    var requesterNationalId: Int? = null

    @SerializedName("eventDayDto")
    @Expose
    var eventDayDto: EventDayDto? = null




    /* "id": 5066,
     "eventDayId": 74,
     "noofTickets": 0,
     "reservationCode": null,
     "requesterNationalId": "29904210101492",
     "eventDayDto": {
         "id": 74,
         "eventDate": "2021-09-12T00:00:00",
         "eventCode": "01",
         "eventName": "القداس الاول",
         "startTime": "06:00:00",
         "endTime": "08:00:00",
         "noofAvailableTickets": 300,
         "noofReservedTickets": 39,
         "notes": "اباء الكنيسة"*/
}