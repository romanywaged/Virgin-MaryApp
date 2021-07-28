package com.example.vergionmaryapp.activities;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.database.RoomFactory;
import com.example.vergionmaryapp.database.asynck.InsertHymensTask;
import com.example.vergionmaryapp.database.entities.Hymens;
import com.example.vergionmaryapp.models.SetUpDatabase;
import com.example.vergionmaryapp.models.SharedPrefrenceModel;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    SharedPrefrenceModel sharedPrefrenceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        move(Hymens, HymensActivity.class);
        move(Booking, BookingActivity.class);



        sharedPrefrenceModel = new SharedPrefrenceModel(HomeActivity.this);
        if (!sharedPrefrenceModel.Check()) {

            SetUpDatabase database = new SetUpDatabase(HomeActivity.this);
            database.readTittle();
            database.setup();
            sharedPrefrenceModel.SaveShared(true);
        }

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