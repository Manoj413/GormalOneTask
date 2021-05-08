package com.gormal.gormalonetask.product.view;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gormal.gormalonetask.product.viewmodel.ProductViewModel;
import com.gormal.gormalonetask.R;
import com.gormal.gormalonetask.databinding.ProductFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductFragment extends Fragment {

    private ProductViewModel mViewModel;
    private ProductFragmentBinding productFragmentBinding;
    private NavHostFragment navHostFragment;
    private Activity mHostActivity;
    Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        productFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.product_fragment, container, false);
        Log.e("home_oncreate","home_oncreate");
        View view = productFragmentBinding.getRoot();

        init();
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
        mHostActivity = (Activity) context;
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        navHostFragment= (NavHostFragment)getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        onClickAddNewProduct();
        onClickSyncData();
    }


    private void onClickAddNewProduct() {
        productFragmentBinding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = NavHostFragment.findNavController(ProductFragment.this);
                navController.navigate(R.id.action_productFragment_to_addProductFragment);
            }
        });
    }

    private void onClickSyncData() {
        productFragmentBinding.btnSyncProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               LiveData<Boolean> productLiveData =  mViewModel.uploadProdcutInfoToServer();
               productLiveData.observe(getActivity(), new Observer<Boolean>() {
                   @Override
                   public void onChanged(Boolean aBoolean) {
                       if (aBoolean){
                           showAlertDialog("Product Pushed","Success");
                       }
                   }
               });
            }
        });
    }

    public void showAlertDialog(String title, String message){
        new MaterialAlertDialogBuilder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mHostActivity.onBackPressed();
                    }
                })
                .show();
    }
}