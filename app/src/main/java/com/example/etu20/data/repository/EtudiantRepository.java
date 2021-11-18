package com.example.etu20.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.etu20.data.model.Etudiant;
import com.example.etu20.data.repository.local.EtudiantRoomDatabase;
import com.example.etu20.data.repository.local.dao.EtudiantDao;
import com.example.etu20.data.repository.remote.EtudiantService;
import com.example.etu20.data.repository.remote.RemoteService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EtudiantRepository {

    private EtudiantDao etudiantDao;
    private MutableLiveData<List<Etudiant>> metudiant = new MutableLiveData<>();
    private EtudiantService etudiantService;

    public EtudiantRepository(Application application) {
        EtudiantRoomDatabase db = EtudiantRoomDatabase.getDatabase(application);
        this.etudiantDao = db.userDao();
        this.etudiantService = RemoteService.getEtudiantService();
    }


    public MutableLiveData<List<Etudiant>> getAll() {

        EtudiantRoomDatabase.databaseWriteExecutor.execute(()->{
            List<Etudiant> users = etudiantDao.getAll();
            metudiant.postValue(users);
        });

        return metudiant;
    }


    public MutableLiveData<Long> insert(Etudiant etudiant) {
        MutableLiveData<Long> mutableLiveData = new MutableLiveData<Long>();

        Call<Etudiant> etudiantCall = this.etudiantService.create(etudiant);
        etudiantCall.enqueue(new Callback<Etudiant>() {
            @Override
            public void onResponse(Call<Etudiant> call, Response<Etudiant> response) {
                Log.d("EtudiantRemoteService", new Gson().toJson(response.body()));

                Etudiant etudiant= response.body();
                if (etudiant != null && etudiant.getId() > 0) {
                    mutableLiveData.postValue(1L);
                    EtudiantRoomDatabase.databaseWriteExecutor.execute(() -> {
                        etudiantDao.insert(etudiant);
                    });
                }
            }

            @Override
            public void onFailure(Call<Etudiant> call, Throwable t) {
                Log.d("UserRemoteService", "onFailure: " + t.getMessage());
                mutableLiveData.postValue(0L);
            }
        });

        return mutableLiveData;
    }

    /*
    private void syncToLocal(List<Etudiant> etudiants) {
        EtudiantRoomDatabase.databaseWriteExecutor.execute(() -> {
            for (Etudiant etudiant : etudiants) {
                etudiantDao.deleteAll();
                etudiantDao.insert(etudiant);
            }
            List<Etudiant> etudiantList = etudiantDao.getAll();
            metudiant.postValue(etudiantList);
        });
    }*/
}
