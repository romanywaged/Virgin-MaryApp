package com.example.vergionmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.booking.showEvents.EventListViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.praise_btn)
    Button Hymens;


    @BindView(R.id.book_aodas_btn)
    CardView bookAodas;

    @BindView(R.id.book_nahda_btn)
    CardView bookNahda;


    @BindView(R.id.about_dev_btn)
    Button About;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        move(Hymens, HymensActivity.class);

        openEventsListView(bookAodas, 1);
        openEventsListView(bookNahda, 2);
    }


    private void move(Button button, final Class c)
    {
        button.setOnClickListener(v ->
        {
            Intent intent = new Intent(HomeActivity.this, c);
            startActivity(intent);
            finish();
        });
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