package  com.church.virginmaryapp.dagger.component

import com.church.virginmaryapp.booking.bookEvent.BookingEventActivity
import com.church.virginmaryapp.booking.showEvents.EventListViewActivity
import com.church.virginmaryapp.dagger.module.AppModule
import com.church.virginmaryapp.dagger.module.NetworkModule
import com.church.virginmaryapp.followReservations.showFollowEvent.FollowReservationActivity
import javax.inject.Singleton
import dagger.Component

/**
 * Created by Marina on 30/6/2019.
 */

@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class)])
interface NetworkComponent
{
//Booking Module
    fun inject(eventListViewActivity : EventListViewActivity)

    fun inject(bookingEventActivity: BookingEventActivity)

    fun inject(followReservationActivity: FollowReservationActivity)
}