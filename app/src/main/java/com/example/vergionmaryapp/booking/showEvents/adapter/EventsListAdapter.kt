package com.example.vergionmaryapp.booking.showEvents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vergionmaryapp.R
import com.example.vergionmaryapp.models.booking.EventModule
import kotlinx.android.synthetic.main.item_event_list.view.*


class EventsListAdapter(private var eventsList : ArrayList<EventModule>,
                        private var listener: IEventsClickListener)
    : RecyclerView.Adapter<EventsListAdapter.EventHolder>()
{

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
        val eventObject = eventsList[position]

        holder.dateNameTv.text = eventObject.eventDate
        holder.bodyDesTv.text = eventObject.eventName
    }

    inner class EventHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val dateNameTv = itemView.dateNameTv!!
        val dayNameTv = itemView.dayNameTv!!
        val bodyDesTv = itemView.bodyDesTv!!
    }
}