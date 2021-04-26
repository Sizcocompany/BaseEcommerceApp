package com.example.baseecommerceapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseecommerceapp.Interface.ItemClickListner;
import com.example.baseecommerceapp.R;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName , txtProductDescription , txtProductPrice , txtProductState  ;
    public ImageView imageViewProduct;
    public ItemClickListner listner;


    public ItemViewHolder(@NonNull View itemView) {
        super( itemView );

        imageViewProduct = itemView.findViewById( R.id.seller_product_image);
        txtProductName = itemView.findViewById( R.id.seller_product_name_details);
        txtProductDescription = itemView.findViewById( R.id.seller_product_description);
        txtProductPrice = itemView.findViewById( R.id.seller_product_price);
    }

//    public void setItemClickListner (ItemClickListner  listner){
//
//        this.listner = listner ;
//    }

    @Override
    public void onClick(View view) {

        listner.onClick( view , getAdapterPosition() , false );
    }
}
