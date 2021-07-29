package com.example.vergionmaryapp.booking.bookEvent

import com.example.vergionmaryapp.base.basePresenter.ParentInterface
import com.example.vergionmaryapp.base.basePresenter.ParentPresenter
import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule

interface IBookingController
{
    interface View : ParentInterface
    {
        fun showLoading()
        fun hideLoading()

        fun submitSuccess(response : BookingResponseModule)
        fun getError(msg : String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun submitBookingObject(bookingObject : RequestBookingBody)
    }
}