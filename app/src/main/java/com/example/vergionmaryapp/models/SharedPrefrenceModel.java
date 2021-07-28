package com.example.vergionmaryapp.models;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefrenceModel {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPrefrenceModel(Context context) {
        this.context = context;
    }

    public void SaveShared(Boolean Saved)
    {
        sharedPreferences= context.getSharedPreferences("Shared",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("First",Saved);
        editor.apply();
    }


    public Boolean Check()
    {
        Boolean share = context.getSharedPreferences("Shared",MODE_PRIVATE).getBoolean("First",false);
        return share;
    }
}
