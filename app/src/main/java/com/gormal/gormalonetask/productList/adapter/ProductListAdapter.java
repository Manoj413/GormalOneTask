package com.gormal.gormalonetask.productList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gormal.gormalonetask.core.ImageLoadTask;
import com.gormal.gormalonetask.databinding.AdapterBooksListBinding;
import com.gormal.gormalonetask.productList.model.Results;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Results> booksArrayList = new ArrayList<>();
    AdapterBooksListBinding booksListBinding;
    public ProductListAdapter(Context context,ArrayList<Results> booksArrayList) {
        this.context = context;
        this.booksArrayList = booksArrayList;
    }

    public ProductListAdapter() {
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /* View view = LayoutInflater.from(context).inflate(R.layout.adapter_prediction_category,parent,false);
        return new CategoryAdapter.ViewHolder(view);*/
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        booksListBinding = AdapterBooksListBinding.inflate(layoutInflater, parent, false);
        return new ProductListAdapter.ViewHolder(booksListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

        holder.bind(booksArrayList.get(position));

    }


    @Override
    public int getItemCount() {
        return booksArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(String.valueOf(booksArrayList.get(position)));
    }

    public void setBookData(ArrayList<Results>  mBooksArrayList){
        booksArrayList = mBooksArrayList;
        notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        private Results books;

        private AdapterBooksListBinding mBinding;
        ViewHolder(AdapterBooksListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Results data)
        {
            this.books = data;
            mBinding.txtBookTitle.setText(books.getBook_name());
            mBinding.txtBookSubtitle.setText(books.getBook_desc());
            mBinding.txtAuthorName.setText(books.getBook_author());
            mBinding.txtPrice.setText(books.getBook_price());


            new ImageLoadTask(books.getBook_img_url(), mBinding.imgBookItem).execute();

        }


        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
            }
        }
    }

}


