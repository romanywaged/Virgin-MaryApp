package com.church.virginmaryapp.followReservations.showFollowEvent

import com.church.virginmaryapp.base.basePresenter.BasePresenter
import com.church.virginmaryapp.booking.showEvents.IEventsController
import com.church.virginmaryapp.models.booking.EventsResponse
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.observers.DisposableObserver

class FollowReservationsPresenter(private var interactor: FollowReservationInteractor, private var IOScheduler: Scheduler,
                                  private var mainscheduler: Scheduler) : BasePresenter<IFollowController.View>(),
        IFollowController.Presenter {
    override fun getFollowControllerEventList(Id: String) {
        checkViewAttached()
        view?.showLoading()

        addDisposable(interactor.getFollowEventsList(Id)
                .subscribeOn(IOScheduler)
                .observeOn(mainscheduler)
                .subscribeWith(object : DisposableObserver<EventsResponse>() {
                    override fun onNext(eventResponse: EventsResponse) {
                        view?.hideLoading()

                        if (eventResponse.eventsList != null) {
                            if (!eventResponse.eventsList!!.isNullOrEmpty()) {
                                view?.getEvents(eventResponse.eventsList!!)
                                view?.emptyList(false)
                            } else
                                view?.emptyList(true)
                        } else
                            view?.getError("لقد حدث خطا في الانترنت")
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

    private fun handleError(e: Throwable) {
        if (e is HttpException)
            when (e.code()) {
                400 -> view?.getError("There is an error")
                500 -> view?.getError("لقد حدث خطا في الانترنت")
                else -> view?.getError("لقد حدث خطا في الانترنت")
            }
        else
            view?.getError("Your internet is unstable")
    }
}