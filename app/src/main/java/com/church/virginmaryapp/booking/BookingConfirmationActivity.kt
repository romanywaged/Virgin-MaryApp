package com.church.virginmaryapp.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.church.virginmaryapp.R
import kotlinx.android.synthetic.main.activity_booking_confirmation.*

class BookingConfirmationActivity : AppCompatActivity() {
    var ticketNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        if (intent != null)
            ticketNumber = intent.getStringExtra("ticketNumberConfirmation").toString()

        booking_number_tv.text = ticketNumber

        confirm_activity_cancel_btn.setOnClickListener {
            finish()
        }
    }
}