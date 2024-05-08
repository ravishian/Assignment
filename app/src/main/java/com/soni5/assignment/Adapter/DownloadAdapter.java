package com.soni5.assignment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soni5.assignment.Model.ModelDownload;
import com.soni5.assignment.R;

import java.util.ArrayList;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.downloadholder>
{


    ArrayList <ModelDownload> datalist;

    public DownloadAdapter(ArrayList<ModelDownload> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public downloadholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.downloadrow, parent, false);
        return new DownloadAdapter.downloadholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull downloadholder holder, int position)
    {
        holder.Adress.setText(datalist.get(position).getCOLUMN_ADDRESS());
        holder.name.setText(datalist.get(position).getCOLUMN_EMAIL());
        holder.dutynumber.setText(datalist.get(position).getTCOLUMN_JOB());

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class  downloadholder extends  RecyclerView.ViewHolder
{
    TextView name , Adress , dutynumber;


    public downloadholder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView28);
        Adress = itemView.findViewById(R.id.textView30);
        dutynumber = itemView.findViewById(R.id.textView29);

    }
}
}
