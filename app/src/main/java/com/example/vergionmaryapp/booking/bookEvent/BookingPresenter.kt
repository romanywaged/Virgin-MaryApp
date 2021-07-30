package com.example.vergionmaryapp.booking.bookEvent

import com.example.vergionmaryapp.base.basePresenter.BasePresenter
import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver

class BookingPresenter (private var interactor : BookingInteractor, private var ioScheduler: Scheduler,
                        private var mainScheduler: Scheduler) : BasePresenter<IBookingController.View>(),
        IBookingController.Presenter
{

    override fun submitBookingObject(bookingObject: RequestBookingBody) {
        checkViewAttached()
        view?.showLoading()

        addDisposable(interactor.submitBookingObject(bookingObject)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribeWith(object : DisposableObserver<BookingResponseModule>()
                {
                    override fun onNext(bookingResponse: BookingResponseModule)
                    {
                        view?.hideLoading()

                        if(bookingResponse.message != null)
                        {
                            if(bookingResponse.message!!.contains("تم تاكيد الحجز بكود"))
                            {
                                val ticketNumber = bookingResponse.message!!.replace("تم تاكيد الحجز بكود","")
                                        .replace(", من فضلك احتفظ بالكود", "")
                                view?.submitSuccess(ticketNumber)

                            } else
                                view?.getError(bookingResponse.message!!)
                        }

                        if(!bookingResponse.errorsObject.isNullOrEmpty())
                        {
                            view?.getError(bookingResponse.errorsObject!![0])
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
