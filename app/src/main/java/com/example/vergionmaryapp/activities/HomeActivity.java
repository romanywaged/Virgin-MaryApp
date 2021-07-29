package com.example.vergionmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.booking.showEvents.EventListViewActivity;
import com.example.vergionmaryapp.models.SetUpDatabase;
import com.example.vergionmaryapp.models.SharedPrefrenceModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
{
    SharedPrefrenceModel sharedPrefrenceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(HomeActivity.this, HymensActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.bookAodasCardView)
    public void onClickBookAodas()
    {
        openElReservationEventsView(1);
    }

    @OnClick(R.id.bookNahdaCardView)
    public void onClickBookNahda()
    {
        openElReservationEventsView(2);
    }

    @OnClick(R.id.aboutCardView)
    public void openAboutView()
    {
        Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
        startActivity(intent);
    }

    private void openElReservationEventsView(int categoryId)
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("actionCategoryId", categoryId);
        startActivity(intent);
    }


}