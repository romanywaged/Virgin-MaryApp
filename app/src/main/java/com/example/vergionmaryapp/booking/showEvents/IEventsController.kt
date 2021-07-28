package com.example.vergionmaryapp.booking.showEvents

import com.example.vergionmaryapp.base.basePresenter.ParentInterface
import com.example.vergionmaryapp.base.basePresenter.ParentPresenter
import com.example.vergionmaryapp.models.booking.EventModule


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