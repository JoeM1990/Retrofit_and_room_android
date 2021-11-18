package com.example.etu20.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "etudiant")
public class Etudiant {

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("matricule")
    @ColumnInfo(name = "matricule")
    private String matricule;

    @SerializedName("nom")
    @ColumnInfo(name = "nom")
    private String nom;

    @SerializedName("postnom")
    @ColumnInfo(name = "postnom")
    private String postnom;

    @SerializedName("prenom")
    @ColumnInfo(name = "prenom")
    private String prenom;

    @SerializedName("age")
    @ColumnInfo(name = "age")
    private String age;

    public Etudiant(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public void setPostnom(String postnom) {
        this.postnom = postnom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
