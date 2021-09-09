package com.church.virginmaryapp.followReservations.showFollowEvent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import com.church.virginmaryapp.R
import kotlinx.android.synthetic.main.activity_enter_national_id.*

class EnterNationalId : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_national_id)
        search_btn.setOnClickListener {
            if (Edit_layout.text.length!=14)
            {
                Toast.makeText(this,"من فضلك ادخل بيانات صحيحه",Toast.LENGTH_LONG).show()
            }
            else
            {
                var s = Edit_layout.text.toString()
                var intent = Intent(this,FollowReservationActivity::class.java)
                intent.putExtra("NationalId",s)
                startActivity(intent)
            }
        }
    }
}