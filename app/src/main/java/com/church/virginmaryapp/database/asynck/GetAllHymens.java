package com.church.virginmaryapp.database.asynck;

import android.os.AsyncTask;

import com.church.virginmaryapp.database.HymensDao;
import com.church.virginmaryapp.database.entities.Hymens;

import java.util.List;

public class GetAllHymens extends AsyncTask<Void,Void, List<Hymens>> {

    HymensDao dao;

    public GetAllHymens(HymensDao dao) {
        this.dao = dao;
    }

    @Override
    protected List<Hymens> doInBackground(Void... voids) {
        return dao.getAllHymens();
    }
}
