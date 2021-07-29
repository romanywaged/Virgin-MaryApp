package com.example.vergionmaryapp.booking.bookEvent

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.vergionmaryapp.MyFreeApplication
import com.example.vergionmaryapp.R
import com.example.vergionmaryapp.models.RequestBookingBody
import com.example.vergionmaryapp.models.booking.BookingResponseModule
import com.example.vergionmaryapp.models.booking.UserObject
import com.example.vergionmaryapp.utils.CommonMethod
import com.example.vergionmaryapp.utils.MyApplicationSharedPreference
import com.example.vergionmaryapp.utils.NothingSelectedSpinnerAdapter
import com.example.vergionmaryapp.utils.ProgressDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_booking_event.*
import kotlinx.android.synthetic.main.activity_reserve_event.*
import java.lang.ref.WeakReference
import javax.inject.Inject


class BookingEventActivity : AppCompatActivity(), IBookingController.View
{
    @Inject
    lateinit var interactor : BookingInteractor

    var presenter : BookingPresenter?= null
    private lateinit var shared : MyApplicationSharedPreference
    private lateinit var dialog : Dialog
    private val commonMethod = CommonMethod()

    private var isAttached = false
    private var isUpdate = false

    private var eventId = 0
    private var userGender = 0
    private var pageTitle = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_event)

        (application as MyFreeApplication).networkComponent?.inject(this)
        presenter = BookingPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread())
        shared = MyApplicationSharedPreference(WeakReference<Context>(this))
        dialog = ProgressDialog.progressDialog(this)

        if(intent != null)
        {
            eventId = intent.getIntExtra("selectedEventId", 0)
            pageTitle = intent.getStringExtra("selectedEventName")!!
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = pageTitle
    }

    override fun onStart()
    {
        super.onStart()
        presenter!!.attachedView(this)
        isAttached = true

        initView()
    }

    override fun showLoading()
    {
        if(isAttached)
            dialog.show()
    }

    private fun initView()
    {
        handleGenderSpinner(genderSpinner)

        confirmBookingBtn.setOnClickListener {
           // validateInputFields()

            val requestBookingBody = RequestBookingBody()
            requestBookingBody.eventDayId = eventId
            requestBookingBody.noofTickets = 1
            requestBookingBody.reservationCode = ""
            requestBookingBody.secretPin = ""
            requestBookingBody.requesterNationalId = "29406090102629"

            val userObject = UserObject()
            val userList = ArrayList<UserObject>()

            userObject.userFullName = "Marina"
            userObject.userNationalId = "29406090102629"
            userObject.userGenderId = 1
            userList.add(userObject)
            requestBookingBody.userObject = userList

            presenter!!.submitBookingObject(requestBookingBody)
        }

        cancelBtn.setOnClickListener {
            finish()
        }
    }

    private fun handleGenderSpinner(mySpinner : Spinner)
    {
        val dayList = ArrayList<String>()
        dayList.add("ذكر")
        dayList.add("انثي")

        val spinnerArrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dayList)

        if(isUpdate)
            mySpinner.adapter = spinnerArrayAdapter

        else
            mySpinner.adapter = NothingSelectedSpinnerAdapter(spinnerArrayAdapter, R.layout.si_gender_title, this)

        var check = 0
        mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long)
            {
                if (++check > 1)
                {
                    when(parent.getItemAtPosition(position) as String)
                    {
                        "ذكر" -> userGender = 1
                        "انثي" -> userGender = 2
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }


    private fun validateInputFields()
    {
        val fullName = fullNameET.text.toString().trim()
        val nationalId = nationalIdET.text.toString().trim()
        val birthdayDate = birthdayDateET.text.toString().trim()

        if(fullName.isNotBlank())
            if(nationalId.isNotBlank())
                if(birthdayDate.isNotBlank())
                    if(userGender != 0)
                    {
                        val requestBookingBody = RequestBookingBody()
                        requestBookingBody.eventDayId = eventId
                        requestBookingBody.noofTickets = 1
                        requestBookingBody.reservationCode = ""
                        requestBookingBody.secretPin = ""
                        requestBookingBody.requesterNationalId = nationalId

                        val userObject = UserObject()
                        val userList = ArrayList<UserObject>()

                        userObject.userFullName = fullName
                        userObject.userNationalId = nationalId
                        userObject.userGenderId = userGender
                        userList.add(userObject)
                        requestBookingBody.userObject = userList

                        presenter!!.submitBookingObject(requestBookingBody)
                    }

//                    else
//                        view!!.appointmentEmpty()
//                else
//                    view!!.addressEmpty()
//            else
//                view!!.specialityEmpty()
//
//        else
//            view!!.nameEmpty()

    }

    override fun submitSuccess(response: BookingResponseModule)
    {
        if(isAttached)
        {
        }
    }

    override fun hideLoading()
    {
        if(isAttached)
            dialog.dismiss()
    }

    override fun getError(msg: String)
    {
        if(isAttached)
            commonMethod.showSnackBarFromString(eventsListContainer, msg)
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
