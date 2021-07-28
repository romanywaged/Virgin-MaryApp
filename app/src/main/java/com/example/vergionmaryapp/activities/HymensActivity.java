package com.example.vergionmaryapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.adapters.HymensAdapter;
import com.example.vergionmaryapp.database.RoomFactory;
import com.example.vergionmaryapp.database.asynck.GetAllHymens;
import com.example.vergionmaryapp.database.asynck.GetAllNamesTask;
import com.example.vergionmaryapp.database.entities.Hymens;
import com.example.vergionmaryapp.models.OnHymensClick;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HymensActivity extends AppCompatActivity implements OnHymensClick {

    @BindView(R.id.Rv_hymens)
    RecyclerView recyclerView;

    List<Hymens> hymens;
    HymensAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hymens);
        ButterKnife.bind(this);

        hymens = new ArrayList<>();
        try {
            hymens = new GetAllHymens(RoomFactory
                    .getHymensDatabaseInstance(HymensActivity.this).getDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new HymensAdapter(hymens, HymensActivity.this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void OnClick(Hymens hymens) {
        Intent intent = new Intent(HymensActivity.this,WordsActivity.class);
        intent.putExtra("Words",hymens.getWords());
        intent.putExtra("Name",hymens.getName());
        startActivity(intent);
    }
}