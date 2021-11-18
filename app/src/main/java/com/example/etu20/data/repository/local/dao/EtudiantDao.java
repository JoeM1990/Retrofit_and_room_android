package com.example.etu20.data.repository.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.etu20.data.model.Etudiant;

import java.util.List;

@Dao
public interface EtudiantDao {

    @Insert
    long insert(Etudiant etudiant);

    @Query("SELECT * FROM etudiant")
    List<Etudiant> getAll();

    @Query("DELETE FROM etudiant WHERE id=:id")
    void delete(long id);

    @Query("DELETE FROM etudiant")
    void deleteAll();

}
