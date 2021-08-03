package com.church.virginmaryapp.database.asynck;

import android.os.AsyncTask;

import com.church.virginmaryapp.database.HymensDao;
import com.church.virginmaryapp.database.entities.Hymens;

public class InsertHymensTask extends AsyncTask<Hymens,Void,Void> {

    HymensDao dao;

    public InsertHymensTask(HymensDao dao) {
        this.dao = dao;
    }

    @Override
    protected Void doInBackground(Hymens... hymens) {
        dao.InsertHymens(hymens[0]);
        return null;
    }
}
