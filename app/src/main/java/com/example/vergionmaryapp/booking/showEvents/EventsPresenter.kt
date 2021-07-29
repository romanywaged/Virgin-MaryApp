package com.example.vergionmaryapp.booking.showEvents

import com.example.vergionmaryapp.base.basePresenter.BasePresenter
import com.example.vergionmaryapp.models.booking.EventsResponse
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver


class EventsPresenter(private var interactor : EventsInteractor, private var ioScheduler: Scheduler,
                      private var mainScheduler: Scheduler) : BasePresenter<IEventsController.View>(),
        IEventsController.Presenter
{
    override fun getEventsList(categoryId: Int)
    {
        checkViewAttached()
        view?.showLoading()

        addDisposable(interactor.getEventsList(categoryId)
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
                            view?.getError("Server Error")
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