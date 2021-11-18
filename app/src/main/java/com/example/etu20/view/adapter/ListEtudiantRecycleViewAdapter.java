package com.example.etu20.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etu20.R;
import com.example.etu20.data.model.Etudiant;

import java.util.List;

public class ListEtudiantRecycleViewAdapter extends RecyclerView.Adapter<ListEtudiantRecycleViewAdapter.EtudiantViewholder>{

    private Context context;
    private List<Etudiant> etudiants;
    private LayoutInflater inflater;


    public ListEtudiantRecycleViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants =  etudiants;
    }

    @NonNull
    @Override
    public EtudiantViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.item_etudiant, parent, false);
        return new EtudiantViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewholder holder, int position) {
        Etudiant etudiant = etudiants.get(position);
        holder.noms.setText(etudiant.getMatricule() + " " + etudiant.getNom() + " "+etudiant.getPostnom() + " "+etudiant.getPrenom() + " "+etudiant.getAge() + " ");
    }

    @Override
    public int getItemCount() {
        return etudiants.size();
    }

    public class EtudiantViewholder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView noms;

        public EtudiantViewholder(View view) {
            super(view);
            item = view.findViewById(R.id.item);
            noms = view.findViewById(R.id.noms);
        }

    }

}
