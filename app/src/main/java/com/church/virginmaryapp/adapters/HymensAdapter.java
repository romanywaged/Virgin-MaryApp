package com.church.virginmaryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.church.virginmaryapp.R;
import com.church.virginmaryapp.database.entities.Hymens;
import com.church.virginmaryapp.models.OnHymensClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HymensAdapter extends RecyclerView.Adapter<HymensAdapter.MyHymensViewHolder> implements Filterable {
    private List<Hymens> hymens;
    private List<Hymens> FilteredHymens;
    private Context context;
    private OnHymensClick hymensClick;
    private Animation fade;
    public HymensAdapter(List<Hymens> hymens, Context context, OnHymensClick click) {
        this.hymens = hymens;
        this.context = context;
        this.hymensClick = click;
        this.FilteredHymens = new ArrayList<>(hymens);
    }

    @NonNull
    @Override
    public HymensAdapter.MyHymensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View u = LayoutInflater.from(context).inflate(R.layout.hymens_row, parent,false);
        return new MyHymensViewHolder(u);
    }

    @Override
    public void onBindViewHolder(@NonNull HymensAdapter.MyHymensViewHolder holder, int position) {
        fade= AnimationUtils.loadAnimation(context,R.anim.scale_animition);
        holder.cardView.setAnimation(fade);
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

    @Override
    public Filter getFilter() {
        return HymensFilter;
    }
    private Filter HymensFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Hymens> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FilteredHymens);
            }else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Hymens hymens:FilteredHymens)
                {
                    if (hymens.getName().toLowerCase().contains(pattern))
                    {
                        filteredList.add(hymens);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hymens.clear();
            hymens.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

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
