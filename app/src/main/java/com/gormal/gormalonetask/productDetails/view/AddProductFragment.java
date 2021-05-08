package com.gormal.gormalonetask.productDetails.view;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.gormal.gormalonetask.core.Validation;
import com.gormal.gormalonetask.productDetails.viewmodel.AddProductViewModel;
import com.gormal.gormalonetask.R;
import com.gormal.gormalonetask.databinding.AddProductFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddProductFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private AddProductFragmentBinding addProductFragmentBinding;
    NavHostFragment navHostFragment;
    Context mContext;
    private Activity mHostActivity;
    AddProductViewModel addProductViewModel;
    Validation mValidation;

    public AddProductFragment() {
        // Required empty public constructor
    }

    public static AddProductFragment newInstance(String param1) {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
        mHostActivity = (Activity) context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        addProductFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_product_fragment, container, false);
        View view = addProductFragmentBinding.getRoot();

        inIt();

        onClickSaveProductDetails();

        return view;
    }

    private void onClickSaveProductDetails() {

        addProductFragmentBinding.btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateFields();

                if (mValidation.validText(addProductFragmentBinding.edtEnterProductName.getText().toString()) &&
                        mValidation.validText(addProductFragmentBinding.edtEnterProductDescription.getText().toString()) &&
                        mValidation.validText(addProductFragmentBinding.edtEnterProductPrice.getText().toString()) &&
                        mValidation.validText(addProductFragmentBinding.edtEnterProductQuantity.getText().toString()))
               {
                   String phone_no = "9890853289";
                  boolean data_pushed =  addProductViewModel.uploadToLocalDatabase(addProductFragmentBinding.edtEnterProductName.getText().toString(),
                                                             addProductFragmentBinding.edtEnterProductDescription.getText().toString(),
                                                            addProductFragmentBinding.edtEnterProductQuantity.getText().toString(),
                                                            addProductFragmentBinding.edtEnterProductPrice.getText().toString(),
                                                             phone_no);

                  showAlertDialog("Alert","Local Data Pushed successfully");
                  Toast.makeText(mContext,"Data Pushed locally..."+data_pushed,Toast.LENGTH_LONG).show();

               }else {
                   Toast.makeText(mContext,"Fill all the details",Toast.LENGTH_LONG).show();
               }
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
    private void validateFields() {
        if (mValidation.validText(addProductFragmentBinding.edtEnterProductName.getText().toString())){
            addProductFragmentBinding.edtEnterProductName.setEnabled(false);
        }else {
            addProductFragmentBinding.edtEnterProductName.setEnabled(true);
            addProductFragmentBinding.edtEnterProductName.setError("Empty Field.");
        }

        if (mValidation.validText(addProductFragmentBinding.edtEnterProductDescription.getText().toString())){
            addProductFragmentBinding.edtEnterProductDescription.setEnabled(false);
        }else {
            addProductFragmentBinding.edtEnterProductDescription.setEnabled(true);
            addProductFragmentBinding.edtEnterProductDescription.setError("Empty Field.");
        }
        if (mValidation.validText(addProductFragmentBinding.edtEnterProductPrice.getText().toString())){
            addProductFragmentBinding.edtEnterProductPrice.setEnabled(false);
        }else {
            addProductFragmentBinding.edtEnterProductPrice.setEnabled(true);
            addProductFragmentBinding.edtEnterProductPrice.setError("Empty Field.");
        }
        if (mValidation.validText(addProductFragmentBinding.edtEnterProductQuantity.getText().toString())){
            addProductFragmentBinding.edtEnterProductQuantity.setEnabled(false);
        }else {
            addProductFragmentBinding.edtEnterProductQuantity.setEnabled(true);
            addProductFragmentBinding.edtEnterProductQuantity.setError("Empty Field.");
        }
    }


    private void inIt() {
        addProductViewModel = new ViewModelProvider(this).get(AddProductViewModel.class);
        mValidation = new Validation();
        navHostFragment= (NavHostFragment)getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}