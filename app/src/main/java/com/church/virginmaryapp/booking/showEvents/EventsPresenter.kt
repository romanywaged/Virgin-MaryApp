package com.church.virginmaryapp.booking.showEvents

import com.church.virginmaryapp.base.basePresenter.BasePresenter
import com.church.virginmaryapp.models.booking.EventsResponse
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver


class EventsPresenter(private var interactor : EventsInteractor, private var ioScheduler: Scheduler,
                      private var mainScheduler: Scheduler) : BasePresenter<IEventsController.View>(),
        IEventsController.Presenter
{
    override fun getEventsList(Id: Int)
    {
        checkViewAttached()
        view?.showLoading()

        addDisposable(interactor.getEventsList(Id)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(object : DisposableObserver<EventsResponse>()
                {
                    override fun onNext(eventsResponse: EventsResponse) {
                        view?.hideLoading()

                        if(eventsResponse.eventsList != null)
                            if(!eventsResponse.eventsList!!.isNullOrEmpty())
                            {
                                view?.getEvents(eventsResponse.eventsList!!)
                                view?.emptyList(false)
                            } else
                                view?.emptyList(true)
                        else
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

    private fun handleError(e: Throwable)
    {
        if (e is HttpException)
            when (e.code())
            {
                400 ->view?.getError("There is an error")
                500 ->view?.getError("لقد حدث خطا في الانترنت")
                else -> view?.getError("لقد حدث خطا في الانترنت")
            }
        else
            view?.getError("Your internet is unstable")
    }
}