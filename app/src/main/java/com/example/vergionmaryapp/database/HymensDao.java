package com.example.vergionmaryapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.vergionmaryapp.database.entities.Hymens;

import java.util.List;

@Dao
public interface HymensDao {


    @Transaction
    @Insert
    void InsertHymens(Hymens hymens);


    @Query("Select * from Hymens")
    List<Hymens> getAllHymens();

    @Query("select name from hymens")
    List<String> getAllNames();
}
