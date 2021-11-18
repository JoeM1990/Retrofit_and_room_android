package com.example.etu20.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.etu20.data.model.Etudiant;
import com.example.etu20.data.repository.EtudiantRepository;

import java.util.List;

public class EtudiantViewModel extends AndroidViewModel {
    private EtudiantRepository mEtudiantRepository;
    private LiveData<List<Etudiant>> metudiant;

    public EtudiantViewModel(@NonNull Application application) {
        super(application);

        this.mEtudiantRepository = new EtudiantRepository(application);
        this.metudiant = mEtudiantRepository.getAll();
    }

    public LiveData<List<Etudiant>> getAll() {
        return metudiant;
    }

    public MutableLiveData<Long> insert(Etudiant etudiant) {
        return this.mEtudiantRepository.insert(etudiant);
    }
}
