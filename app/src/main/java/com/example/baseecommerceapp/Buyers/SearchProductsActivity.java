package com.example.baseecommerceapp.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.baseecommerceapp.Model.Products;
import com.example.baseecommerceapp.R;
import com.example.baseecommerceapp.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchProductsActivity extends AppCompatActivity {

    private RecyclerView searchList;
    RecyclerView.LayoutManager layoutManager;
    private Button searchBtn;
    private EditText inputeSearchText;
    private String searchInpute = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products);

        inputeSearchText = findViewById(R.id.search_productName);
        searchBtn = findViewById(R.id.search_productBtn);

        searchList = findViewById(R.id.search_listView);
        searchList.setLayoutManager(new LinearLayoutManager(SearchProductsActivity.this));


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchInpute = inputeSearchText.getText().toString();
                onStart();


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference searchRef = FirebaseDatabase.getInstance().getReference().child("Products");


        // we will search with pname
        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(searchRef.orderByChild("pname").startAt(searchInpute), Products.class)
                        .build();

        // we add startAt to show all items in search start with first charcter like when we write i search result ( islam , iphone ect )

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull Products products) {

                        holder.txtProductName.setText(products.getPname());
                        holder.txtProductDescription.setText(products.getDescription());
                        holder.txtProductPrice.setText(getString(R.string.price )+ products.getPrice() + "LE");
                        Picasso.get().load(products.getImage()).into(holder.imageViewProduct);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(SearchProductsActivity.this, ProductdetailsActivity.class);
                                //to get spacific product ID inorder to view it
                                intent.putExtra("pid", products.getPid());

                                startActivity(intent);
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}