package com.example.nhom5_oderfood.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.Interface.ItemClickListener;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.viewhoder>{
    ArrayList<MonAn> list;
    Context context;
    MonAnDAO monAnDAO;
    ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public MonAnAdapter(Context context,ArrayList<MonAn> list){
        this.context = context;
        this.list = list;
        monAnDAO = new MonAnDAO(context);
    }
    @NonNull
    @Override
    public viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_monan,parent,false);
        return new viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhoder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if(itemClickListener != null){
                itemClickListener.onclick(position);
            }
        });
        MonAn monAn = list.get(position);
        Glide.with(context)
                .load(monAn.getHinhMA())
                .into(holder.imageView);

        holder.tv_ten.setText(monAn.getTenMA());
        holder.tv_gia.setText(String.format("%,d",monAn.getGiaMA())+"\t"+"VNĐ");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewhoder extends RecyclerView.ViewHolder{
         ImageView imageView;
         TextView tv_ten,tv_gia;
        public viewhoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_monan);
            tv_ten = itemView.findViewById(R.id.tv_tenmonan);
            tv_gia = itemView.findViewById(R.id.tv_giamonan);
        }


    }
}
