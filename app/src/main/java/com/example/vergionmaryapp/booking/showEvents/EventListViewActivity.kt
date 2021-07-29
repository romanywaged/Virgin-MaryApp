package com.example.vergionmaryapp.booking.showEvents

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vergionmaryapp.MyFreeApplication
import com.example.vergionmaryapp.R
import com.example.vergionmaryapp.booking.showEvents.adapter.EventsListAdapter
import com.example.vergionmaryapp.booking.showEvents.adapter.IEventsClickListener
import com.example.vergionmaryapp.models.booking.EventModule
import com.example.vergionmaryapp.utils.CommonMethod
import com.example.vergionmaryapp.utils.MyApplicationSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_el_nahda.view.*
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

class EventListViewActivity : AppCompatActivity(), IEventsController.View, IEventsClickListener
{
    @Inject
    lateinit var interactor : EventsInteractor

    var presenter : EventsPresenter?= null
    private var parentView : View ?= null
    private lateinit var shared : MyApplicationSharedPreference

    private lateinit var mAdapter: EventsListAdapter
    private val myEventsList = ArrayList<EventModule>()
    private lateinit var mLayoutManager: LinearLayoutManager
    private val commonMethod = CommonMethod()

    private var isAttached = false
    private var categoryId = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_el_nahda)

        (application as MyFreeApplication).networkComponent?.inject(this)
        presenter = EventsPresenter(interactor, Schedulers.io(), AndroidSchedulers.mainThread())
        shared = MyApplicationSharedPreference(WeakReference<Context>(this))

        if(intent != null)
            categoryId = intent.getIntExtra("actionCategoryId", 0)
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
            parentView!!.event_progress_bar.visibility = View.VISIBLE
            presenter!!.getEventsList(categoryId)
        } else
            commonMethod.showSnackBarFromResource(parentView!!.eventsListContainer, R.string.no_internet_connection, this)
    }

    private fun initRecyclerView()
    {
        initAdapter()
        parentView!!.events_list.addOnScrollListener(object: RecyclerView.OnScrollListener()
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
        parentView!!.events_list.layoutManager = mLayoutManager

        mAdapter = EventsListAdapter(myEventsList, this)
        parentView!!.events_list.adapter = mAdapter
    }

    override fun showLoading()
    {
        if(isAttached)
            parentView!!.event_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading()
    {
        if(isAttached)
            parentView!!.event_progress_bar.visibility = View.GONE
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
            parentView!!.emptyList.visibility = View.VISIBLE
        else
            parentView!!.emptyList.visibility = View.GONE
    }

    override fun getError(msg: String) {
        if(isAttached)
            commonMethod.showSnackBarFromString(parentView!!.eventsListContainer, msg)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        presenter!!.detachedView()
        isAttached = false
    }

    override fun onItemClicked(eventId: Int) {
    }
}