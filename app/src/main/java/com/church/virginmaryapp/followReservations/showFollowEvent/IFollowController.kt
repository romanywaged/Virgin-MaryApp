package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.base.basePresenter.ParentInterface
import com.church.virginmaryapp.base.basePresenter.ParentPresenter
import com.church.virginmaryapp.models.booking.EventModule
import com.church.virginmaryapp.models.follow.FollowEventModule

interface IFollowController {
    interface View : ParentInterface
    {
        fun showLoading()
        fun hideLoading()

        fun getEvents(eventsList : List<FollowEventModule>)


        fun emptyList(isEmpty : Boolean)
        fun getError(msg : String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun getFollowControllerEventList(Id: String)


    }
}