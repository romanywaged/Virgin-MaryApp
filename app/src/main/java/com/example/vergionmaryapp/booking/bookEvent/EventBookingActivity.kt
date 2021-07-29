package com.example.vergionmaryapp.booking.bookEvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.vergionmaryapp.R

class EventBookingActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_booking)
        val id = intent.getIntExtra("ID",0)

        Toast.makeText(this,id ,Toast.LENGTH_LONG)
    }
}
