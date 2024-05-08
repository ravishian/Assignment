package com.soni5.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "Default");
        String email = sharedPreferences.getString("email", "Default");
        String name = sharedPreferences.getString("name", "Default");
        String empid = sharedPreferences.getString("emp_id", "Default");
        String firstname = sharedPreferences.getString("first_name", "Default");
        String lastname = sharedPreferences.getString("last_name", "Default");


        System.out.println(token + email+name+empid+firstname+lastname);



        if (!token.equals("Default"))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);


        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);

        }



    }
}