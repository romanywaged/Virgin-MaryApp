package com.church.virginmaryapp.followReservations.showFollowEvent.adapter

import com.church.virginmaryapp.models.booking.EventModule

interface IFollowReservationsItemClick {
    fun clickEventToDelete(eventModule:EventModule)
}