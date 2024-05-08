package com.soni5.assignment;

import static com.soni5.assignment.R.id.nav_home;
import static com.soni5.assignment.R.id.nav_profile;
import static com.soni5.assignment.R.id.search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationBarView;
import com.soni5.assignment.Fragment.DownloadFragment;
import com.soni5.assignment.Fragment.HomeFragment;
import com.soni5.assignment.Fragment.ProfileFragment;
import com.soni5.assignment.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {




    private RequestQueue requestQueue;
    ActivityHomeBinding homeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        requestQueue = Volley.newRequestQueue(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "Default");
        String email = sharedPreferences.getString("email", "Default");
        String name = sharedPreferences.getString("name", "Default");
        String empid = sharedPreferences.getString("emp_id", "Default");
        String firstname = sharedPreferences.getString("first_name", "Default");
        String lastname = sharedPreferences.getString("last_name", "Default");
     //
        replaceFragment(new HomeFragment());


        homeBinding.bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == nav_home) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == search) {
                    replaceFragment(new HomeFragment());
                } else if (itemId == R.id.nav_notification) {
                    replaceFragment(new DownloadFragment());
                } else if (itemId == nav_profile) {
                    replaceFragment(new ProfileFragment());
                }
                return true;
            }
        });












       // System.out.println(token + email+name+empid+firstname+lastname);



    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment existingFragment = fragmentManager.findFragmentById(R.id.frame_layout);
        if (existingFragment == null || !existingFragment.getClass().equals(fragment.getClass())) {
            fragmentTransaction.replace(R.id.frame_layout, fragment).addToBackStack(null);
        }
        fragmentTransaction.setReorderingAllowed(false);
        fragmentTransaction.commit();
    }





}

