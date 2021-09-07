package com.church.virginmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.church.virginmaryapp.R;
import com.church.virginmaryapp.adapters.SlidehomeAdapter;
import com.church.virginmaryapp.booking.showEvents.EventListViewActivity;
import com.church.virginmaryapp.models.PagerClass;
import com.church.virginmaryapp.models.SetUpDatabase;
import com.church.virginmaryapp.models.SharedPrefrenceModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity
{
    SharedPrefrenceModel sharedPrefrenceModel;
    private List<PagerClass> pagers;
    private SlidehomeAdapter adapter;



  //  @BindView(R.id.homePager)
    //ViewPager viewPager;

   // @BindView(R.id.tabLayout)
   // TabLayout tabLayout;


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



        pagers=new ArrayList<>();

        //Slider
        pagers.add(new PagerClass(R.drawable.vergionmary));
        pagers.add(new PagerClass(R.drawable.vergion));
        pagers.add(new PagerClass(R.drawable.churcch));
        pagers.add(new PagerClass(R.drawable.churchh));
        adapter=new SlidehomeAdapter(HomeActivity.this,pagers);
     //   viewPager.setAdapter(adapter);
      //  tabLayout.setupWithViewPager(viewPager,true);


        Timer timer=new Timer();
       // timer.scheduleAtFixedRate(new HomeActivity.SliderTimer(),4000,4000);


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
        openElReservationEventsView(1, getString(R.string.booking_aodas));
    }

    @OnClick(R.id.aboutCardView)
    public void openAboutView()
    {
        Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
        startActivity(intent);
    }

    private void openElReservationEventsView(int categoryId, String eventName)
    {
        Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
        intent.putExtra("eventCategoryId", categoryId);
        intent.putExtra("eventTypeName", eventName);
        startActivity(intent);
    }




   /* class SliderTimer extends TimerTask {
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem()<pagers.size()-1)
                    {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }
                    else
                        viewPager.setCurrentItem(0);
                }
            });
        }
    }*/



}