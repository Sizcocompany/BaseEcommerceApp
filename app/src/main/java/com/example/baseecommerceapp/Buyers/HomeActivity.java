package com.example.baseecommerceapp.Buyers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baseecommerceapp.Admin.AdminCategoryActivity;
import com.example.baseecommerceapp.Model.Products;
import com.example.baseecommerceapp.Prevalent.Prevalent;
import com.example.baseecommerceapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;


    private ImageView nuts, honey, dates, baked;
    private ImageView olive_oil, yamiesh, coffee;

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Products products = new Products();
        nuts = findViewById(R.id.user_nutes_image);
        honey = findViewById(R.id.user_honey_image);
        dates = findViewById(R.id.user_dates_image);
        baked = findViewById(R.id.user_baked_image);
        olive_oil = findViewById(R.id.user_olive_oil);
        yamiesh = findViewById(R.id.user_yamiseh_image);
        coffee = findViewById(R.id.user_coffee);

        onClickCardviews(nuts , "nuts");

        onClickCardviews(honey , "honey");
        onClickCardviews(dates , "dates");

        onClickCardviews(baked , "baked");

        onClickCardviews(olive_oil , "olive_oil");

        onClickCardviews(yamiesh , "yamiesh");

        onClickCardviews(coffee , "coffee");

        // this  check to know if admin or user to provide it in case press on maintain btn
        // as of not added add will crash as user condition  not handel
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            // to set type with what will get from admin category activity when press on maintain btn
            type = getIntent().getExtras().get("Admin").toString();
        }

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Paper.init(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.home));
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this is to allow only users to access cart activity
                if (!type.equals("Admin")) {

                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);

                } else {

                    fab.setVisibility(View.GONE);
                }

            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // initialize header view ( nav_header_main.xml )
        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }


        if (!type.equals("Admin")) {

            userNameTextView.setText(Prevalent.currentOnlineUsers.getName());
            Picasso.get().load(Prevalent.currentOnlineUsers.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        }

//        recyclerView = findViewById(R.id.recycler_menu);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(Home2Activity.this);
//        recyclerView.setLayoutManager(layoutManager);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {

            // this is to allow only users to access this
            if (!type.equals("Admin")) {

                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);

            }


        } else if (id == R.id.nav_search) {
// this is to allow only users to access this
            if (!type.equals("Admin")) {

                Intent intent = new Intent(HomeActivity.this, SearchProductsActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_settings) {

            // this is to allow only users to access this
            if (!type.equals("Admin")) {

            }

            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {

            // this is to allow only users to access this
            if (!type.equals("Admin")) {

                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (type.equals("Admin")) {
            Intent intent = new Intent(HomeActivity.this, AdminCategoryActivity.class);
            startActivity(intent);
            finish();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.really_exit))
                    .setMessage(getString(R.string.are_you_sure_you_want_to_exit))
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            setResult(RESULT_OK, new Intent().putExtra("EXIT", true));
                            finish();
                        }

                    }).create().show();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    // create one method when customer press in ( image view ) send to user show catagry page to show the category
    // which choose ( we will add the category name in string  )
    public void onClickCardviews(ImageView cardView, final String string) {

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , User_showCategoryActivity.class);
                intent.putExtra("category" , string);
                startActivity(intent);
            }
        });

    }
}