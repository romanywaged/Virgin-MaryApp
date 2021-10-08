package com.church.virginmaryapp.booking

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        booking_number_tv.setOnClickListener {
            copyToBoard()
        }
    }

    //Copy Words ToBoard
    private fun copyToBoard() {
        val manager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Copied!", booking_number_tv.text.toString())
        manager.setPrimaryClip(clipData)
        Toast.makeText(this, "Copied", Toast.LENGTH_LONG).show()
    }

}