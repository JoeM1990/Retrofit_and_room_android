package com.example.etu20.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.etu20.R;
import com.example.etu20.data.model.Etudiant;
import com.example.etu20.databinding.FragmentSecondBinding;
import com.example.etu20.vm.EtudiantViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondFragment extends Fragment {

    @BindView(R.id.btnAdd)
    TextView btnAdd;

    @BindView(R.id.btnDel)
    TextView btnDel;

    @BindView(R.id.btnUpd)
    TextView btnUpd;

    private Etudiant etudiant;
    private EtudiantViewModel etudiantViewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.etudiantViewModel=new ViewModelProvider(requireActivity()).get(EtudiantViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentSecondBinding fragmentSecondBinding = FragmentSecondBinding.inflate(inflater);
        View view = fragmentSecondBinding.getRoot();
        ButterKnife.bind(this, view);

        this.etudiant=new Etudiant();
        fragmentSecondBinding.setEtudiant(etudiant);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindEvents();
        this.navController= Navigation.findNavController(view);
    }

    public void bindEvents() {
        btnAdd.setOnClickListener(view -> {

            MutableLiveData<Long> repObs=this.etudiantViewModel.insert(this.etudiant);

            repObs.observe(requireActivity(),rep->{
                Log.d("REPINSERT", "bindEvents rep insert: "+rep);
                if(rep>0){
                    //NavDirections navDirections=SecondFragmentDirections.actionSecondFragmentToFirstFragment();
                    //this.navController.navigate(navDirections);
                    NavHostFragment.findNavController(SecondFragment.this).navigate(R.id.action_secondFragment_to_firstFragment);
                }
                repObs.removeObservers(requireActivity());
            });
        });
    }
}
