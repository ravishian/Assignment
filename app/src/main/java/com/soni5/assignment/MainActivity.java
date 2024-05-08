package com.soni5.assignment;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String LOGIN_URL = "https://guardroaster.com/api/login/token";
    private RequestQueue requestQueue;
    SharedPreferences.Editor editor;
    Button login;
    EditText email,password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login_button);
        email = findViewById(R.id.editTextemail);
        password = findViewById(R.id.edittextpassword);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                login(email.getText().toString(), password.getText().toString());

            }
        });


        requestQueue = Volley.newRequestQueue(this);
       SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
       editor = sharedPreferences.edit();







    }

    private void login(String email, String password) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int statusCode = response.getInt("statusCode");
                            String message  =  response.getString("message");
                            System.out.println(message);

                            if (statusCode == 200) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, ""+response.getString("message"), Toast.LENGTH_SHORT).show();
                                JSONObject data = response.getJSONObject("data");
                                String token = data.getString("token");

                                editor.putString("token", token);
                                editor.putString("emp_id", data.getString("emp_id"));
                                editor.putString("name", data.getString("name"));
                                editor.putString("email", data.getString("email"));
                                editor.putString("first_name", data.getString("first_name"));
                                editor.putString("last_name", data.getString("last_name"));
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {

                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String errorMessage = new String(error.networkResponse.data, "UTF-8");
                        JSONObject errorObject = new JSONObject(errorMessage);
                        int statusCode = errorObject.getInt("statusCode");
                        String message = errorObject.getString("message");
                        Toast.makeText(MainActivity.this, ""+ message, Toast.LENGTH_SHORT).show();
                        System.out.println("Error message: " + message);
                        System.out.println("Error status code: " + statusCode);
                        progressBar.setVisibility(View.GONE);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        requestQueue.add(request);
    }
}