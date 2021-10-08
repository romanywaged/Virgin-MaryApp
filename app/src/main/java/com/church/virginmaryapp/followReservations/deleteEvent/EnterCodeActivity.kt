package com.church.virginmaryapp.followReservations.deleteEvent

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.church.virginmaryapp.MyFreeApplication
import com.church.virginmaryapp.R
import com.church.virginmaryapp.booking.BookingConfirmationActivity
import com.church.virginmaryapp.booking.bookEvent.BookingPresenter
import com.church.virginmaryapp.followReservations.showFollowEvent.FollowReservationActivity
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.utils.CommonMethod
import com.church.virginmaryapp.utils.MyApplicationSharedPreference
import com.church.virginmaryapp.utils.ProgressDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_booking_event.*
import kotlinx.android.synthetic.main.activity_enter_code.*
import kotlinx.android.synthetic.main.activity_enter_national_id.*
import kotlinx.android.synthetic.main.si_gender_title.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class EnterCodeActivity : AppCompatActivity() , ICancelingController.View
{

    @Inject
    lateinit var interactor: CancelingInteractor


    var presenter:CancelingPresenter? = null

    private lateinit var dialog : Dialog
    private lateinit var shared : MyApplicationSharedPreference
    private val commonMethod = CommonMethod()
    private var isAttached = false

    private lateinit var followEventModule: FollowEventModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_code)
        (application as MyFreeApplication).networkComponent?.inject(this)
        presenter = CancelingPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread())
        shared = MyApplicationSharedPreference(WeakReference<Context>(this))
        dialog = ProgressDialog.progressDialog(this)



        if(intent != null)
        {
            followEventModule = (intent.getSerializableExtra("Object") as? FollowEventModule)!!
        }


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachedView(this)
        isAttached = true
        initView()
    }

    private fun initView() {
        cancel_btn_Code.setOnClickListener {
            if (Edit_layoutCode.text.trim().length == 16)
            {
                followEventModule.reservationCode = Edit_layoutCode.text.toString().trim()
                presenter!!.submitCancelingObject(followEventModule)
                Edit_layoutCode.text.clear()
                val intent = Intent(this,FollowReservationActivity::class.java)
                startActivity(intent)
            }
            else
            {
                commonMethod.showSnackBarFromResource(cancel_reservation_container, R.string.data_error, this)
            }
        }
    }


    override fun showLoading() {
        if(isAttached)
            dialog.show()
    }

    override fun hideLoading() {
        if(isAttached)
            dialog.dismiss()
    }

    override fun getError(msg: String) {
        if(isAttached)
            commonMethod.showSnackBarFromString(cancel_reservation_container, msg)
    }

    override fun submitSuccess(msg: String) {
        if(isAttached)
        {
            Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
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

    override fun onDestroy()
    {
        super.onDestroy()
        presenter!!.detachedView()
        isAttached = false
    }
}