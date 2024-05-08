package com.soni5.assignment.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.soni5.assignment.Adapter.InfoAdapter;
import com.soni5.assignment.Model.Model_Jobs;
import com.soni5.assignment.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {

    private static final String API_URL = "https://guardroaster.com/api/complete-guard-jobs";
     RequestQueue requestQueue;

     TextView name,jobs;
    RecyclerView recyclerView,recyclerView2;
    ArrayList<Model_Jobs> datalist;
    InfoAdapter infoAdapter;
    ProgressBar progressBar;

    public HomeFragment()
    {


    }



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        requestQueue = Volley.newRequestQueue(getContext());

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String Token = sharedPreferences.getString("token", "Default");


        datalist = new ArrayList<>();
        name = view.findViewById(R.id.textView4);
        jobs = view.findViewById(R.id.textView7);

        String names = sharedPreferences.getString("first_name", "Default");
        name.setText("Welcome "+names);

        progressBar = view.findViewById(R.id.progressBar2);
        recyclerView = view.findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        infoAdapter= new InfoAdapter(datalist);
        recyclerView.setAdapter(infoAdapter);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle API Response
                        try {

                            JSONObject dataObject = response.getJSONObject("data");
                            JSONArray recordsArray = dataObject.getJSONArray("records");

                            for (int i = 0; i < recordsArray.length(); i++) {
                                JSONObject record = recordsArray.getJSONObject(i);
                                String site = record.getString("site");
                                String siteAddress = record.getString("site_address");
                                String siteImage = record.getString("site_image");
                                String siteType = record.getString("site_type");
                                String dutyNumber = record.getString("duty_number");
                                String checkinTime = record.getString("checkin_time");
                                String checkoutTime = record.getString("checkout_time");
                                String pdfBill = record.getString("pdf_bill");

                                datalist.add(new Model_Jobs(site,siteAddress,siteImage,siteType,dutyNumber,checkinTime,checkoutTime,pdfBill));

                            }
                            infoAdapter.notifyDataSetChanged();
                            jobs.setText(recordsArray.length()+ " "+response.getString("message") );
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        System.out.println(error.getMessage());

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Authorization", "Bearer " + Token);
                return headers;
            }
        };


        requestQueue.add(jsonObjectRequest);

        return  view;
    }
}