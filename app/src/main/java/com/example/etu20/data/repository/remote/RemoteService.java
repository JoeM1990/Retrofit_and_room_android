package com.example.etu20.data.repository.remote;

import com.example.etu20.data.model.Etudiant;
import com.example.etu20.utils.UrlConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteService {

    private static Retrofit retrofit;

    private RemoteService(){

    }

    private static Retrofit newInstance(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(UrlConfig.BASE_URL)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getRequestHeader(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }

    public static EtudiantService getEtudiantService(){
        newInstance();
        EtudiantService etudiantService=retrofit.create(EtudiantService.class);
        return etudiantService;
    }

}
