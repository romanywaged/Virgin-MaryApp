package com.church.virginmaryapp.models;

import android.content.Context;

import com.church.virginmaryapp.database.RoomFactory;
import com.church.virginmaryapp.database.asynck.InsertHymensTask;
import com.church.virginmaryapp.database.entities.Hymens;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetUpDatabase {
    List<String> hymens_names = new ArrayList<>();
    String words;
    public List<Hymens> hymensList = new ArrayList<>();
    Context context;

    public SetUpDatabase(Context context) {
        this.context = context;
    }


    public void readFile(String FileName)
    {
        try {
            InputStream is;
            is = context.getAssets().open(FileName+".txt");
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            words = new String(buffer);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }




    public void readTittle()
    {
        String tittles = "";
        try {
            InputStream is;
            is = context.getAssets().open("Tittles.txt");
            int size = is.available();
            byte [] buffer = new byte[size];
            is.read(buffer);
            is.close();
            tittles = new String(buffer);
            hymens_names = new ArrayList<>(Arrays.asList(tittles.split("//")));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public void setup()
    {
        for (int i = 0; i<hymens_names.size() ;i++) {
            readFile(+ i +"");
            hymensList.add(new Hymens(hymens_names.get(i), words));
        }

        for (int j = 0; j<hymensList.size(); j++)
        {

            new InsertHymensTask(RoomFactory.getHymensDatabaseInstance(context)
                    .getDao()).execute(hymensList.get(j));
        }
    }

}
