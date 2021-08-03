package com.church.virginmaryapp.database;

import android.content.Context;

public class RoomFactory {

    private static HymensDatabase hymensDatabaseInstance;

    public static HymensDatabase getHymensDatabaseInstance(Context context)
    {
        hymensDatabaseInstance = HymensDatabase.getInstance(context);
        return hymensDatabaseInstance;
    }

}
