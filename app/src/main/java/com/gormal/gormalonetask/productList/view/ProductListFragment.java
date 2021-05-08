package com.gormal.gormalonetask.productList.view;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gormal.gormalonetask.productDetails.viewmodel.AddProductViewModel;
import com.gormal.gormalonetask.productList.adapter.ProductListAdapter;
import com.gormal.gormalonetask.productList.model.Books;
import com.gormal.gormalonetask.productList.model.Results;
import com.gormal.gormalonetask.productList.viewmodel.ProductListViewModel;
import com.gormal.gormalonetask.R;
import com.gormal.gormalonetask.databinding.ProductListFragmentBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ProductListFragmentBinding productListFragmentBinding;
    NavHostFragment navHostFragment;
    Context mContext;
    private Activity mHostActivity;
    ProductListViewModel productListViewModel;
    ProductListAdapter productListAdapter;
    ArrayList<Results> booksArrayList;
    RecyclerView.LayoutManager layoutManager;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(String param1) {
        ProductListFragment fragment = new ProductListFragment();
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
        productListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.product_list_fragment, container, false);
        productListFragmentBinding.setProductListViewModel(new ProductListViewModel());
        View view = productListFragmentBinding.getRoot();

        Log.e("ProductListFragment","ProductListFragment");
        inIt();
        return view;
    }


    private void inIt() {
        productListViewModel = new ViewModelProvider(this).get(ProductListViewModel.class);
        navHostFragment= (NavHostFragment)getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        booksArrayList = new ArrayList<>();
        getBookData();
        observeLoader();
    }

    private void observeLoader() {
        productListViewModel.getLoading().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    productListFragmentBinding.vFullLoader.setVisibility(View.VISIBLE);
                    productListFragmentBinding.vFullLoader.setmLoadingText("Fetching the data ...");
                }else {
                    productListFragmentBinding.vFullLoader.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getBookData() {
        productListAdapter = new ProductListAdapter(getContext(),booksArrayList);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        productListFragmentBinding.rVProductList.setLayoutManager(layoutManager);
        productListFragmentBinding.rVProductList.setAdapter(productListAdapter);


        productListViewModel.getBooksData().observe(getActivity(), new Observer<Books>() {
            @Override
            public void onChanged(Books books) {
                setProductAdapter(books.getResults());
            }
        });
    }

    private void setProductAdapter(ArrayList<Results> results) {
        productListAdapter.setBookData(results);
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