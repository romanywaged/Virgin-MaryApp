package com.example.vergionmaryapp.models

import com.example.vergionmaryapp.models.booking.UserObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestBookingBody : Serializable
{
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

    @SerializedName("secretPin")
    @Expose
    var secretPin: String? = null

    @SerializedName("eventReservationTicketResources")
    @Expose
    var userObject: List<UserObject>? = null


//    {
//        "eventDayId": 1,
//        "noofTickets": 1,
//        "reservationCode": "string",
//        "requesterNationalId": "29904210101492",
//        "secretPin": "string",
//        "eventReservationTicketResources": [
//        {
//            "nationalId": "29904210101492",
//            "fullName": "Marina",
//            "genderId": 1
//        }
//        ]
//    }
}