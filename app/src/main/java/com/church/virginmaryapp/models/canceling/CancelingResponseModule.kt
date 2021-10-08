package com.church.virginmaryapp.models.canceling

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CancelingResponseModule {

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
    var response: String? = null
}