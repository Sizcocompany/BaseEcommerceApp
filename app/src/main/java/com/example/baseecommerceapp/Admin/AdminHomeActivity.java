package com.example.baseecommerceapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.baseecommerceapp.Buyers.HomeActivity;
import com.example.baseecommerceapp.Buyers.LoginActivity;
import com.example.baseecommerceapp.R;

public class AdminHomeActivity extends AppCompatActivity {
    private Button checkOrdersBtn , adminLogoutBtn , maintainProductsBtn ,  checkApproveProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        checkOrdersBtn = findViewById(R.id.check_orders_btn);
        adminLogoutBtn = findViewById(R.id.admin_logout_btn);
        maintainProductsBtn = findViewById(R.id.maintain_btn);
        checkApproveProductsBtn = findViewById(R.id.check_approve_Productsbtn);

        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this , HomeActivity.class);
                // we will send to homeactivity with string to can defratiate between admin and user
                intent.putExtra("Admin" , "Admin");
                startActivity(intent);
            }
        });


        checkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this , AdminNewOrdersActivity.class);
                startActivity(intent);

            }
        });

        checkApproveProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this , AdminCheckApproveNewProductActivity.class);
                // below to don't allow user to back again
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });

        adminLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this , LoginActivity.class);
                // below to don't allow user to back again
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
    }
}