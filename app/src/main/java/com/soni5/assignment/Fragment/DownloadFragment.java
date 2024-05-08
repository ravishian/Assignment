package com.soni5.assignment.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soni5.assignment.Adapter.DownloadAdapter;
import com.soni5.assignment.Adapter.InfoAdapter;
import com.soni5.assignment.Database.DB;
import com.soni5.assignment.Model.ModelDownload;
import com.soni5.assignment.Model.Model_Jobs;
import com.soni5.assignment.R;

import java.util.ArrayList;


public class DownloadFragment extends Fragment {


    DB db;
    ArrayList<ModelDownload> datalist;
    RecyclerView recyclerView;
    DownloadAdapter downloadAdapter;
    public DownloadFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_download, container, false);

        db  = new DB(getActivity());
        datalist = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recylerviewdownload);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        downloadAdapter= new DownloadAdapter(datalist);
        recyclerView.setAdapter(downloadAdapter);

        displaydata();

        return view;
    }

    void displaydata()
    {
        Cursor cursor = db.getAllData();
        if (cursor.getCount() != 0)
        {
            while (cursor.moveToNext())
            {
                datalist.add(new ModelDownload(cursor.getString(0),cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3)
                        ,cursor.getString(4)));
            }
        }

    }
}