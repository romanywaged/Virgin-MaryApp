package com.church.virginmaryapp.booking.showEvents

import com.church.virginmaryapp.base.basePresenter.ParentInterface
import com.church.virginmaryapp.base.basePresenter.ParentPresenter
import com.church.virginmaryapp.models.booking.EventModule


interface IEventsController
{
    interface View : ParentInterface
    {
        fun showLoading()
        fun hideLoading()

        fun getEvents(eventsList : List<EventModule>)

        fun emptyList(isEmpty : Boolean)
        fun getError(msg : String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun getEventsList(categoryId: Int)
    }
}