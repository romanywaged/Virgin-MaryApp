package com.example.vergionmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.booking.showEvents.EventListViewActivity;
import com.example.vergionmaryapp.models.SetUpDatabase;
import com.example.vergionmaryapp.models.SharedPrefrenceModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
{
    @BindView(R.id.praise_lin)
    LinearLayout Hymens;

    @BindView(R.id.book_aodas_btn)
    CardView bookAodas;

    @BindView(R.id.book_nahda_btn)
    CardView bookNahda;

    @BindView(R.id.about_dev_btn)
    Button About;

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

    @Override
    protected void onStart() {
        super.onStart();

        Hymens.setOnClickListener(v ->
        {
            Intent intent = new Intent(HomeActivity.this, HymensActivity.class);
            startActivity(intent);
            finish();
        });

        openEventsListView(bookAodas, 1);
        openEventsListView(bookNahda, 2);
    }

    private void openEventsListView(CardView bookBtn, int categoryId)
    {
        bookBtn.setOnClickListener(v ->
        {
            Intent intent = new Intent(HomeActivity.this, EventListViewActivity.class);
            intent.putExtra("actionCategoryId", categoryId);
            startActivity(intent);
            finish();
        });
    }


}