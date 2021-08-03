package com.church.virginmaryapp.models.booking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserObject : Serializable
{
    @SerializedName("nationalId")
    @Expose
    var userNationalId: String? = null

    @SerializedName("fullName")
    @Expose
    var userFullName: String? = null

    @SerializedName("genderId")
    @Expose
    var userGenderId: Int? = null

    @SerializedName("birthDate")
    @Expose
    var userBirthDate: String? = null
}