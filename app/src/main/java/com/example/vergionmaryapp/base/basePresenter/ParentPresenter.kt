package com.example.vergionmaryapp.base.basePresenter

interface ParentPresenter<V : ParentInterface>
{
    fun attachedView(mvpView: V)
    fun detachedView()
}
