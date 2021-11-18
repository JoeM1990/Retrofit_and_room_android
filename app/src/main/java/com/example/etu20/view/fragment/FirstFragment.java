package com.example.etu20.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.etu20.R;
import com.example.etu20.view.adapter.ListEtudiantRecycleViewAdapter;
import com.example.etu20.vm.EtudiantViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FirstFragment extends Fragment{

    @BindView(R.id.list_etudiants)
    RecyclerView list_etudiants;

    @BindView(R.id.btnAdd2)
    TextView btnAdd2;

    private EtudiantViewModel etudiantViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.etudiantViewModel=new ViewModelProvider(requireActivity()).get(EtudiantViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_first,container,false);
        ButterKnife.bind(this,view);
        init();
        bindEvents();
        return view;
    }



    public void init(){

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false);
        list_etudiants.setLayoutManager(linearLayoutManager);

        ListEtudiantRecycleViewAdapter listEtudiantRecycleViewAdapter=new  ListEtudiantRecycleViewAdapter(requireContext());
        listEtudiantRecycleViewAdapter.setEtudiants(new ArrayList<>());
        list_etudiants.setAdapter(listEtudiantRecycleViewAdapter);


        this.etudiantViewModel.getAll().observe(requireActivity(),etudiants->{
            listEtudiantRecycleViewAdapter.setEtudiants(etudiants);
            listEtudiantRecycleViewAdapter.notifyDataSetChanged();
        });

    }


    public void bindEvents(){
        btnAdd2.setOnClickListener(view -> {
            NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_firstFragment_to_secondFragment);
        });
    }


}
