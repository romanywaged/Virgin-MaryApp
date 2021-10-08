package com.church.virginmaryapp.followReservations.showFollowEvent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.church.virginmaryapp.MyFreeApplication
import com.church.virginmaryapp.R
import com.church.virginmaryapp.booking.showEvents.EventsPresenter
import com.church.virginmaryapp.booking.showEvents.IEventsController
import com.church.virginmaryapp.booking.showEvents.adapter.EventsListAdapter
import com.church.virginmaryapp.followReservations.deleteEvent.CancelingPresenter
import com.church.virginmaryapp.followReservations.deleteEvent.EnterCodeActivity
import com.church.virginmaryapp.followReservations.deleteEvent.ICancelingController
import com.church.virginmaryapp.followReservations.deleteEvent.RequestCancelingEvent
import com.church.virginmaryapp.followReservations.showFollowEvent.adapter.FollowReservationsAdapter
import com.church.virginmaryapp.followReservations.showFollowEvent.adapter.IFollowReservationsItemClick
import com.church.virginmaryapp.models.booking.EventModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.utils.CommonMethod
import com.church.virginmaryapp.utils.MyApplicationSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_follow_resrvation_activty.*
import kotlinx.android.synthetic.main.activity_reserve_event.*
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

class FollowReservationActivity : AppCompatActivity(), IFollowController.View, IFollowReservationsItemClick {



    @Inject
    lateinit var interactor:FollowReservationInteractor
    var presenter:FollowReservationsPresenter?=null
    private lateinit var shared : MyApplicationSharedPreference
    private lateinit var mAdapter: FollowReservationsAdapter
    private val myEventsList = ArrayList<FollowEventModule>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private val commonMethod = CommonMethod()

    private var isAttached = false
    private var NationalId = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow_resrvation_activty)

        (application as MyFreeApplication).networkComponent?.inject(this)
        presenter = FollowReservationsPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread())
        shared = MyApplicationSharedPreference(WeakReference<Context>(this))

        if(intent != null)
        {
            NationalId = intent.getStringExtra("NationalId").toString()
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        supportActionBar!!.title = "متابعة حجوزاتى"
    }

    override fun onStart() {
        super.onStart()
        presenter!!.attachedView(this)
        isAttached = true
        initView()
    }

    private fun initView()
    {
        initRecyclerView()

        if(commonMethod.checkNetworkConnection(this))
        {
            myEventsList.clear()
            followProgress.visibility = View.VISIBLE
            presenter!!.getFollowControllerEventList(NationalId)
        } else
            commonMethod.showSnackBarFromResource(eventsListContainer, R.string.no_internet_connection, this)
    }

    private fun initRecyclerView()
    {
        initAdapter()
        follow_evnt_list.addOnScrollListener(object: RecyclerView.OnScrollListener()
        {
            @SuppressLint("LongLogTag")
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun initAdapter()
    {
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        follow_evnt_list.layoutManager = mLayoutManager

        mAdapter = FollowReservationsAdapter(myEventsList, this, this)
        follow_evnt_list.adapter = mAdapter
    }

    override fun showLoading()
    {
        if(isAttached)
            followProgress.visibility = View.VISIBLE
    }

    override fun hideLoading()
    {
        if(isAttached)
            followProgress.visibility = View.GONE
    }

    override fun getEvents(eventsList: List<FollowEventModule>)
    {
        if(isAttached)
        {
            myEventsList.addAll(eventsList)
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun emptyList(isEmpty : Boolean)
    {
        if(isEmpty)
            Follo_emptyList.visibility = View.VISIBLE
        else
            Follo_emptyList.visibility = View.GONE
    }

    override fun getError(msg: String) {
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

    override fun clickEventToDelete(followEventModule: FollowEventModule) {
        val intent:Intent = Intent(this, EnterCodeActivity::class.java)
        intent.putExtra("Object",followEventModule)
        startActivity(intent)

    }
}