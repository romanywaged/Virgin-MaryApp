package com.church.virginmaryapp.booking.bookEvent

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.church.virginmaryapp.MyFreeApplication
import com.church.virginmaryapp.R
import com.church.virginmaryapp.booking.BookingConfirmationActivity
import com.church.virginmaryapp.models.RequestBookingBody
import com.church.virginmaryapp.models.booking.UserObject
import com.church.virginmaryapp.utils.CommonMethod
import com.church.virginmaryapp.utils.MyApplicationSharedPreference
import com.church.virginmaryapp.utils.NothingSelectedSpinnerAdapter
import com.church.virginmaryapp.utils.ProgressDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_booking_event.*
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

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

    private var pageTitle = ""
    private var birthdayWithoutSpace = ""
    private var fullBirthday = ""
    private var eventId = 0
    private var userGender = 0

    val myCalender = Calendar.getInstance()

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

        val datePicker = DatePickerDialog.OnDateSetListener{
            _, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            updateLable(myCalender)

        }

        birthdayDateET.setOnClickListener {
       var dialog = DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,datePicker, myCalender.get(Calendar.YEAR)
                    ,myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DAY_OF_MONTH)).show()

        }
    }

    private fun updateLable(myCalender: Calendar) {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        fullBirthday = sdf.format(myCalender.time)
        birthdayWithoutSpace = fullBirthday.replace("-","")
        birthdayDateET.setText(fullBirthday)
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
        birthdayDateET.inputType = 0

        confirmBookingBtn.setOnClickListener {
            validateInputFields()
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

        val spinnerArrayAdapter = ArrayAdapter<String>(this, R.layout.si_items_gender, dayList)

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
                        if(commonMethod.validateNationalID(nationalId, birthdayWithoutSpace))
                            if(commonMethod.validateGender(nationalId, userGender))
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
                                userObject.userBirthDate = fullBirthday

                                userList.add(userObject)
                                requestBookingBody.userObject = userList

                                presenter!!.submitBookingObject(requestBookingBody)

                            }else
                                commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.data_error, this)
                        else
                            commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.data_error, this)
                    else
                        commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.gender_error, this)
                else
                    commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.birthday_error, this)
            else
                commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.national_id_error, this)
        else
            commonMethod.showSnackBarFromResource(bookingEventContainer, R.string.name_error, this)
    }





    override fun submitSuccess(ticketNumber : String)
    {
        if(isAttached)
        {
            val intent = Intent(this, BookingConfirmationActivity::class.java)
            intent.putExtra("ticketNumberConfirmation" , ticketNumber)
            startActivity(intent)
            finish()
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
            commonMethod.showSnackBarFromString(bookingEventContainer, msg)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
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
