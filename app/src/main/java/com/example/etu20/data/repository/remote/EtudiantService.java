package com.example.etu20.data.repository.remote;

import com.example.etu20.data.model.Etudiant;
import com.example.etu20.utils.UrlConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EtudiantService {

    @POST(UrlConfig.ETUDIANT)
    Call<Etudiant> create(@Body Etudiant etudiant);

    @GET(UrlConfig.ETUDIANT)
    Call<List<Etudiant>> getAll();

    @DELETE(UrlConfig.ETUDIANT+"/{id}")
    Call<Etudiant> delete(@Path("id") int id);

}
