package com.church.virginmaryapp.followReservations.showFollowEvent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.church.virginmaryapp.R
import com.church.virginmaryapp.models.booking.EventModule
import com.church.virginmaryapp.models.follow.FollowEventModule
import com.church.virginmaryapp.utils.CommonMethod
import kotlinx.android.synthetic.main.follow_reservation_row.view.*
import kotlinx.android.synthetic.main.item_event_list.view.*

class FollowReservationsAdapter (private var eventList:ArrayList<FollowEventModule>,
                                 private var listener:IFollowReservationsItemClick,
                                 private var context:Context)
                                 : RecyclerView.Adapter<FollowReservationsAdapter.MyFollowViewHolder>()
{
    private var fade: Animation? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFollowViewHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.follow_reservation_row,parent,false)
        return MyFollowViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyFollowViewHolder, position: Int) {
        fade = AnimationUtils.loadAnimation(context, R.anim.scale_animition)
        holder.myCardView.animation = fade

        val eventObject = eventList[position]
        val commonMethod = CommonMethod()
        var eventDate = ""

        if (eventObject.eventDayDto?.eventDate != null && eventObject.eventDayDto?.eventDate != "00:00:00")
            eventDate = eventObject.eventDayDto?.eventDate?.let { commonMethod.separateString(it) }.toString()

        holder.eventTitleTv.text = eventObject.eventDayDto?.eventName
        holder.eventDateTv.text = eventDate
        holder.eventDayTv.text = commonMethod.getDayNameFromDate(eventDate)
        holder.fromTimeTv.text = eventObject.eventDayDto?.endTime?.substring(0, 5)?.let { commonMethod.convert24Hto12H(it) }
        holder.toTimeTv.text = eventObject.eventDayDto?.endTime?.substring(0, 5)?.let { commonMethod.convert24Hto12H(it) }


        holder.cancel_btn.setOnClickListener {
            listener.clickEventToDelete(eventObject)
        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }
    class MyFollowViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val eventDateTv = itemView.eventDateTv_follow!!
        val eventDayTv = itemView.eventDayTv_follow!!
        val eventTitleTv = itemView.eventTitleTv_follow!!
        val fromTimeTv = itemView.from_time_follow!!
        val toTimeTv = itemView.to_time_follow!!
        val myCardView = itemView.RV_follow_bg!!
        val cancel_btn = itemView.cancel_reservation_btn_follow!!
    }
}
