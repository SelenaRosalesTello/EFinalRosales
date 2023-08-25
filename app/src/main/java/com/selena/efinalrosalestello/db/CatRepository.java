package com.selena.efinalrosalestello.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.selena.efinalrosalestello.model.ShowEntity;

import java.util.List;

public class CatRepository {

    private CatDao dao;
    private CatDatabase db;

    public CatRepository(Application application){
        db = CatDatabase.getInstance(application);
        dao = db.catDao();
    }

    public void addShow(ShowEntity showEntity){
        CatDatabase.dataBaseWriteExecutor.execute(() ->
                dao.addShow(showEntity));
    }

    public LiveData<List<ShowEntity>> getAll(){
        return dao.getAll();
    }
}
