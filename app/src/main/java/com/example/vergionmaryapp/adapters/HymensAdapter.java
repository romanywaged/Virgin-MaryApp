package com.example.vergionmaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vergionmaryapp.R;
import com.example.vergionmaryapp.database.entities.Hymens;
import com.example.vergionmaryapp.models.OnHymensClick;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HymensAdapter extends RecyclerView.Adapter<HymensAdapter.MyHymensViewHolder> {
    private List<Hymens> hymens;
    private Context context;
    private OnHymensClick hymensClick;
    public HymensAdapter(List<Hymens> hymens, Context context, OnHymensClick click) {
        this.hymens = hymens;
        this.context = context;
        this.hymensClick = click;
    }

    @NonNull
    @Override
    public HymensAdapter.MyHymensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View u = LayoutInflater.from(context).inflate(R.layout.hymens_row, parent,false);
        return new MyHymensViewHolder(u);
    }

    @Override
    public void onBindViewHolder(@NonNull HymensAdapter.MyHymensViewHolder holder, int position) {
        holder.hymenName.setText(hymens.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hymensClick.OnClick(hymens.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return hymens.size();
    }

    public class MyHymensViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hymens_tittle)
        TextView hymenName;

        @BindView(R.id.cardview)
        CardView cardView;

        public MyHymensViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
