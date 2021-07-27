package com.example.vergionmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vergionmaryapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.praise_btn)
    Button Hymens;


    @BindView(R.id.Add_DB_btn)
    Button Booking;


    @BindView(R.id.about_dev_btn)
    Button About;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        move(Hymens, HymensActivity.class);
        move(Booking, BookingActivity.class);

    }


    private void move(Button button, final Class c)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, c);
                startActivity(intent);
                finish();
            }
        });
    }


}