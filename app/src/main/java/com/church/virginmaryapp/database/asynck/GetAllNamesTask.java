package com.church.virginmaryapp.database.asynck;

import android.os.AsyncTask;

import com.church.virginmaryapp.database.HymensDao;

import java.util.List;

public class GetAllNamesTask extends AsyncTask<Void,Void, List<String>> {

    HymensDao dao;

    public GetAllNamesTask(HymensDao dao) {
        this.dao = dao;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        return dao.getAllNames();
    }
}
