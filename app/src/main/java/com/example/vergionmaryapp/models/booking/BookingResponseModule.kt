package com.example.vergionmaryapp.models.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BookingResponseModule : Serializable
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

}