package com.gormal.gormalonetask.home.view;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gormal.gormalonetask.home.viewmodel.HomeViewModel;
import com.gormal.gormalonetask.R;
import com.gormal.gormalonetask.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private HomeFragmentBinding homeBinding;
    private NavHostFragment navHostFragment;
    private Activity mHostActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater,
                R.layout.home_fragment, container, false);
        Log.e("home_oncreate","home_oncreate");

        init();
        return homeBinding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mHostActivity = (Activity) context;
    }


    private void init() {

        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        onClickAddProduct();
        onClickBooks();
    }

    private void onClickAddProduct() {
        homeBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                navController.navigate(R.id.action_homeFragment_to_productFragment);
            }
        });
    }

    private void onClickBooks() {
        homeBinding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(HomeFragment.this);
                navController.navigate(R.id.action_homeFragment_to_productListFragment);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }


}