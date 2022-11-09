package com.example.courseproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.courseproject.Data.Users;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    Context context;
    List<Users> usersList;

    public RVAdapter(Context context, List<Users> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recview_items,parent,false);

        return new RVAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        Users users =  usersList.get(position);
        holder.nameTv.setText(users.getFullName());
        holder.mobTv.setText(users.getMobNum());
        holder.addressTv.setText(users.getAddress());

        LocalDate intervalDay = LocalDate.parse(users.getNextDon());
        LocalDate thisDay = LocalDate.now();
        long range = ChronoUnit.DAYS.between(thisDay, intervalDay);

        holder.nextDonTV.setText(Long.toString(range));


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTv,mobTv,addressTv,nextDonTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.fNameTV);
            mobTv = itemView.findViewById(R.id.mobNameTV);
            addressTv = itemView.findViewById(R.id.addressTVRV);
            nextDonTV = itemView.findViewById(R.id.nextDonDayRVTV);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Users usersRV = usersList.get(getAbsoluteAdapterPosition());
            Intent intent = new Intent(context,DonorAllInfo.class);
            intent.putExtra("donorsRV",usersRV);
            context.startActivity(intent);

        }
    }
}
