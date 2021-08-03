package com.church.virginmaryapp.base.basePresenter

interface ParentPresenter<V : ParentInterface>
{
    fun attachedView(mvpView: V)
    fun detachedView()
}
