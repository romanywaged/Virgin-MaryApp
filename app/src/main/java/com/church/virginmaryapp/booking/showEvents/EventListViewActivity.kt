package com.church.virginmaryapp.booking.showEvents

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.church.virginmaryapp.MyFreeApplication
import com.church.virginmaryapp.R
import com.church.virginmaryapp.booking.bookEvent.BookingEventActivity
import com.church.virginmaryapp.booking.showEvents.adapter.EventsListAdapter
import com.church.virginmaryapp.booking.showEvents.adapter.IEventsClickListener
import com.church.virginmaryapp.models.booking.EventModule
import com.church.virginmaryapp.utils.CommonMethod
import com.church.virginmaryapp.utils.MyApplicationSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reserve_event.*
import java.lang.ref.WeakReference
import java.util.*
import javax.inject.Inject

class EventListViewActivity : AppCompatActivity(), IEventsController.View, IEventsClickListener
{
    @Inject
    lateinit var interactor : EventsInteractor

    var presenter : EventsPresenter?= null
    private lateinit var shared : MyApplicationSharedPreference

    private lateinit var mAdapter: EventsListAdapter
    private val myEventsList = ArrayList<EventModule>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private val commonMethod = CommonMethod()

    private var isAttached = false

    private var categoryId = 0
    private var pageTitle = ""


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_event)

        (application as MyFreeApplication).networkComponent?.inject(this)
        presenter = EventsPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread())
        shared = MyApplicationSharedPreference(WeakReference<Context>(this))

        if(intent != null)
        {
            categoryId = intent.getIntExtra("eventCategoryId", 0)
            pageTitle = intent.getStringExtra("eventTypeName")!!
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

    private fun initView()
    {
        initRecyclerView()

        if(commonMethod.checkNetworkConnection(this))
        {
            myEventsList.clear()
            event_progress_bar.visibility = View.VISIBLE
            presenter!!.getEventsList(categoryId)
        } else
            commonMethod.showSnackBarFromResource(eventsListContainer, R.string.no_internet_connection, this)
    }

    private fun initRecyclerView()
    {
        initAdapter()
        events_list.addOnScrollListener(object: RecyclerView.OnScrollListener()
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
        events_list.layoutManager = mLayoutManager

        mAdapter = EventsListAdapter(myEventsList, this, this)
        events_list.adapter = mAdapter
    }

    override fun showLoading()
    {
        if(isAttached)
            event_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading()
    {
        if(isAttached)
            event_progress_bar.visibility = View.GONE
    }

    override fun getEvents(eventsList: List<EventModule>)
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
            emptyList.visibility = View.VISIBLE
        else
            emptyList.visibility = View.GONE
    }

    override fun getError(msg: String) {
        if(isAttached)
            commonMethod.showSnackBarFromString(eventsListContainer, msg)
    }

    override fun onItemClicked(eventId: Int) {
        intent = Intent(this, BookingEventActivity::class.java)
        intent.putExtra("selectedEventId",eventId)
        intent.putExtra("selectedEventName", pageTitle)
        startActivity(intent)
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