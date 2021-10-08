package com.church.virginmaryapp.followReservations.deleteEvent

import com.church.virginmaryapp.base.basePresenter.ParentInterface
import com.church.virginmaryapp.base.basePresenter.ParentPresenter
import com.church.virginmaryapp.models.follow.FollowEventModule

interface ICancelingController {

    interface View : ParentInterface
    {
        fun showLoading()
        fun hideLoading()

        fun getError(msg : String)
        fun submitSuccess(msg: String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun submitCancelingObject(CancelingObject : FollowEventModule)
    }
}