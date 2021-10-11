package com.church.virginmaryapp.followReservations.showFollowEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.church.virginmaryapp.R
import com.church.virginmaryapp.utils.CommonMethod
import kotlinx.android.synthetic.main.activity_enter_code.*
import kotlinx.android.synthetic.main.activity_enter_national_id.*

class EnterNationalId : AppCompatActivity() {

    private val commonMethod = CommonMethod()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_national_id)



        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "ادخل الرقم القومى"

        search_btn.setOnClickListener {
            if (commonMethod.checkNetworkConnection(this)) {
                if (Edit_layout.text.length != 14) {
                    Toast.makeText(this, "من فضلك ادخل بيانات صحيحه", Toast.LENGTH_LONG).show()
                } else {
                    val s = Edit_layout.text.toString()
                    val intent = Intent(this, FollowReservationActivity::class.java)
                    intent.putExtra("NationalId", s)
                    startActivity(intent)
                    Edit_layout.text.clear()
                }
            } else
                commonMethod.showSnackBarFromResource(national_id_container, R.string.no_internet_connection, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}