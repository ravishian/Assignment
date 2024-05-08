package com.soni5.assignment.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.soni5.assignment.MainActivity;
import com.soni5.assignment.R;


public class ProfileFragment extends Fragment {


    TextView name,email,eployeid;
    Button logout;

    public ProfileFragment() {
        // Required empty public constructor

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String emails = sharedPreferences.getString("email", "Default");
        String names = sharedPreferences.getString("name", "Default");
        String empids = sharedPreferences.getString("emp_id", "Default");




        name = view.findViewById(R.id.nametext);
        email = view.findViewById(R.id.emailname);
        eployeid = view.findViewById(R.id.employeid);
        logout = view.findViewById(R.id.buttonlog);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        name.setText("Name : "+names);
        email.setText("Email : "+emails);
        eployeid.setText("Emp id : "+empids);


        return  view;
    }
}