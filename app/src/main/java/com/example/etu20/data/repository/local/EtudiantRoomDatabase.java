package com.example.etu20.data.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.etu20.data.model.Etudiant;
import com.example.etu20.data.repository.local.dao.EtudiantDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Etudiant.class}, version = 1, exportSchema = false)
public abstract class EtudiantRoomDatabase extends RoomDatabase {

    public abstract EtudiantDao userDao();

    private static volatile EtudiantRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static EtudiantRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EtudiantRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EtudiantRoomDatabase.class, "ak47").build();
                }
            }
        }
        return INSTANCE;
    }

}
