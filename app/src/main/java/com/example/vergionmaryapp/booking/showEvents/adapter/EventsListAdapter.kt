package com.example.vergionmaryapp.booking.showEvents.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.vergionmaryapp.R
import com.example.vergionmaryapp.models.booking.EventModule
import com.example.vergionmaryapp.utils.CommonMethod
import kotlinx.android.synthetic.main.item_event_list.view.*


class EventsListAdapter(private var eventsList : ArrayList<EventModule>,
                        private var listener: IEventsClickListener,
                        private var context : Context)
    : RecyclerView.Adapter<EventsListAdapter.EventHolder>()
{
    private var fade: Animation? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder
    {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_event_list, parent, false)

        return EventHolder(view)
    }

    override fun getItemCount(): Int
    {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int)
    {
        fade = AnimationUtils.loadAnimation(context, R.anim.scale_animition)
        holder.myCardView.animation = fade

        val eventObject = eventsList[position]
        val commonMethod = CommonMethod()
        var eventDate = ""

        if(eventObject.eventDate != null && eventObject.eventDate != "00:00:00")
            eventDate = eventObject.eventDate?.let { commonMethod.separateString(it) }.toString()

        holder.eventTitleTv.text = eventObject.eventName
        holder.eventDateTv.text = eventDate
        holder.eventDayTv.text = commonMethod.getDayNameFromDate(eventDate)
        holder.fromTimeTv.text = eventObject.startTime?.substring(0,5)?.let { commonMethod.convert24Hto12H(it) }
        holder.toTimeTv.text = eventObject.endTime?.substring(0,5)?.let { commonMethod.convert24Hto12H(it) }
        if(eventObject.notes != null)
            holder.noteTv.text = eventObject.notes

        holder.myCardView.setOnClickListener {
            eventObject.id?.let { listener.onItemClicked(it) }
        }.toString()
    }

    inner class EventHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val eventDateTv = itemView.eventDateTv!!
        val eventDayTv = itemView.eventDayTv!!
        val eventTitleTv = itemView.eventTitleTv!!
        val fromTimeTv = itemView.from_time!!
        val toTimeTv = itemView.to_time!!
        val myCardView = itemView.iteme_event_linear!!
        val noteTv = itemView.noteTv!!
    }
}