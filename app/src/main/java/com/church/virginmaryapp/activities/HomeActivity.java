package com.church.virginmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.church.virginmaryapp.R;
import com.church.virginmaryapp.booking.showEvents.EventListViewActivity;
import com.church.virginmaryapp.followReservations.showFollowEvent.EnterNationalId;
import com.church.virginmaryapp.models.SetUpDatabase;
import com.church.virginmaryapp.models.SharedPrefrenceModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
{
    SharedPrefrenceModel sharedPrefrenceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        ButterKnife.bind(this);

        sharedPrefrenceModel = new SharedPrefrenceModel(HomeActivity.this);
        if (!sharedPrefrenceModel.Check())
        {
            SetUpDatabase database = new SetUpDatabase(HomeActivity.this);
            database.readTittle();
            database.setup();
            sharedPrefrenceModel.SaveShared(true);
        }

    }

    @OnClick(R.id.taranimCardView)
    public void openHymensActivity()
    {
        move(HymensActivity.class);
    }


    @OnClick(R.id.bookAodasCardView)
    public void onClickBookAodas()
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("eventCategoryId", 1);
        intent.putExtra("eventTypeName", getString(R.string.booking_aodas));
        startActivity(intent);
    }


    @OnClick(R.id.aboutCardView)
    public void openAboutView()
    {
        move(AboutActivity.class);
    }


    @OnClick(R.id.aya_card)
    public void openAyaCard()
    {

    }


    @OnClick(R.id.follow_card)
    public void openFollowReservation()
    {
        move(EnterNationalId.class);
    }


    // lma kan 3ndna nahda
    private void openElReservationEventsView(int categoryId, String eventName)
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("eventCategoryId", categoryId);
        intent.putExtra("eventTypeName", eventName);
        startActivity(intent);
    }



    private void move(Class c)
    {
        Intent intent = new Intent(HomeActivity.this,c);
        startActivity(intent);
    }
}