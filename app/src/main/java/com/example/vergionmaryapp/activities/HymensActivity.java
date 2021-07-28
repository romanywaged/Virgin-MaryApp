package com.example.vergionmaryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("ترانيم وتماجيد العدرا");
    }

    @Override
    public void OnClick(Hymens hymens) {
        Intent intent = new Intent(HymensActivity.this,WordsActivity.class);
        intent.putExtra("Words",hymens.getWords());
        intent.putExtra("Name",hymens.getName());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem searchitem=menu.findItem(R.id.search2);
        SearchView searchView=(SearchView)searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}