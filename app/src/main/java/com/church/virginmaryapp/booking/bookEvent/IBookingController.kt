package com.church.virginmaryapp.booking.bookEvent

import com.church.virginmaryapp.base.basePresenter.ParentInterface
import com.church.virginmaryapp.base.basePresenter.ParentPresenter
import com.church.virginmaryapp.models.RequestBookingBody

interface IBookingController
{
    interface View : ParentInterface
    {
        fun showLoading()
        fun hideLoading()

        fun submitSuccess(ticketNumber : String)
        fun getError(msg : String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun submitBookingObject(bookingObject : RequestBookingBody)
    }
}