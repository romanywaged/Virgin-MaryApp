package com.church.virginmaryapp.followReservations.deleteEvent

import com.church.virginmaryapp.base.basePresenter.BasePresenter
import com.church.virginmaryapp.models.canceling.CancelingResponseModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

class CancelingPresenter (private var interactor : CancelingInteractor, private var ioScheduler: Scheduler,
                          private var mainScheduler: Scheduler) : BasePresenter<ICancelingController.View>(),
        ICancelingController.Presenter
{

    override fun submitCancelingObject(cancelingObject: FollowEventModule) {
        checkViewAttached()
        view?.showLoading()

        addDisposable(interactor.submitCancelingEventObject(cancelingObject)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(object : DisposableObserver<CancelingResponseModule>()
                {
                    override fun onNext(cancelingResponse: CancelingResponseModule)
                    {
                        view?.hideLoading()

                        if(cancelingResponse.message != null)
                        {
                            if(cancelingResponse.message!!.contains("تم الغاء الحجز بنجاح"))
                            {
                                view?.submitSuccess("تم الغاء الحجز بنجاح")

                            } else
                                view?.getError(cancelingResponse.message!!)
                        }

                        if(!cancelingResponse.errorsObject.isNullOrEmpty())
                        {
                            view?.getError(cancelingResponse.errorsObject!![0])
                        }

                    }

                    override fun onComplete() {
                        view?.hideLoading()
                    }

                    override fun onError(e: Throwable) {
                        view?.hideLoading()
                        handleError(e)
                    }
                }))
    }

    private fun handleError(e: Throwable)
    {
        if (e is HttpException)
            when (e.code())
            {
                400 ->view?.getError("There is an error")
                500 ->view?.getError("Server Error")
            }
        else
            view?.getError("Your internet is unstable")
    }
}