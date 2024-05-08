package com.soni5.assignment.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.soni5.assignment.Database.DB;
import com.soni5.assignment.R;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class DetailFragment extends Fragment {


    TextView name,adress,type,dutyno,checkin,checkout;
    String names,adresss,types,dutynos,checkins,checkouts,link,bill;
    ImageView imageView;
    Button button;

    public DetailFragment() {
        // Required empty public constructor
    }

    public DetailFragment(String site, String siteAddress, String siteImage,
                          String dutyNumber, String checkinTime,
                          String checkoutTime, String pdfBill, String siteType) {

        names = site;
        adresss = siteAddress;
        types = siteType;
        dutynos = dutyNumber;
        checkins = checkinTime;
        checkouts = checkoutTime;
        link = siteImage;
        bill = pdfBill;

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_detail, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "Default");

        name = view.findViewById(R.id.textView11);
        adress = view.findViewById(R.id.textView12);
        type = view.findViewById(R.id.textView13);
        dutyno = view.findViewById(R.id.textView14);
        checkin = view.findViewById(R.id.textView15);
        checkout = view.findViewById(R.id.textView16);
        imageView  = view.findViewById(R.id.imageView5);
        button = view.findViewById(R.id.button3);


        name.setText("Name : "+names);
        adress.setText("Adress : "+adresss);
        type.setText("Type : "+types);
        dutyno.setText("Duty : "+dutynos);
        checkin.setText("Check in : "+checkins);
        checkout.setText("check out : "+checkouts);
        Picasso.get().load(link).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getContext(), ""+link, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Choose Download Method");

                builder.setNegativeButton("Download Manager ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {upload(dutynos,adresss,link,email,names);
                        downloadpdf(bill);
                    }
                });

                builder.setPositiveButton("Using Browser", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {  upload(dutynos,adresss,link,email,names);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(bill)));
                    }
                });
                builder.show();

            }
        });



        return view;
    }
    void downloadpdf(String url)
    {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("PDF Download");
        request.setDescription("Downloading PDF file");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, names);
        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    void upload(String job , String Adress,String link , String email ,String name )
    {
        DB mydb = new DB(getActivity());
        mydb.addData(job,Adress,link,email,name);
    }

}