package com.soni5.assignment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.soni5.assignment.Fragment.DetailFragment;
import com.soni5.assignment.Fragment.ProfileFragment;
import com.soni5.assignment.Model.Model_Jobs;
import com.soni5.assignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.infoholder>
{
    ArrayList<Model_Jobs> datalist;

    public InfoAdapter(ArrayList<Model_Jobs> datalist) {
        this.datalist = datalist;
    }


    @NonNull
    @Override
    public infoholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        return new infoholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull infoholder holder, int position)
    {

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                replaceFragment(new DetailFragment(datalist.get(position).getSite()
                        ,datalist.get(position).getSiteAddress(),datalist.get(position).getSiteImage(),
                        datalist.get(position).getDutyNumber(),datalist.get(position).getCheckinTime(),
                        datalist.get(position).getCheckoutTime(),datalist.get(position).getPdfBill()
                        ,datalist.get(position).getSiteType()),v);
            }
        });
        holder.name.setText(datalist.get(position).getSite());
        holder.adress.setText(datalist.get(position).getSiteAddress());
        holder.duty.setText(datalist.get(position).getDutyNumber());
        Picasso.get().load(datalist.get(position).getSiteImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class infoholder extends RecyclerView.ViewHolder {
        TextView name,adress,duty;
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public infoholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView8);
            adress = itemView.findViewById(R.id.textView10);
            duty = itemView.findViewById(R.id.textView9);
            imageView = itemView.findViewById(R.id.imageView3);
            constraintLayout = itemView.findViewById(R.id.constraintLayout1);
        }
    }

    private  void replaceFragment(Fragment fragment , View view)
    {
        AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();
        FragmentManager fragmentmanger =  appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentmanger.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment).addToBackStack(null);
        fragmentTransaction.setReorderingAllowed(false);
        fragmentTransaction.detach(fragment).attach(fragment).commitAllowingStateLoss();



        // fragmentTransaction.commit();
    }
}
