package com.example.nhom5_oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom5_oderfood.DTO.GioHang;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.AppDatabase;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.Viewholder>{
    private List<GioHang> list;
    Context context;


    public void setData(Context context,List<GioHang> list){
        this.context = context;
       this.list = list;
       notifyDataSetChanged();
    }
    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (GioHang gioHang : list) {
            totalPrice += gioHang.getGiaGH();
        }
        return totalPrice;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_themvaogiohang2,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        GioHang gioHang = list.get(position);
        if(gioHang == null){
            return;
        }
        String tenAnh = gioHang.getHinhanhGH();
        int imageResourceId = context.getResources().getIdentifier(tenAnh, "drawable", context.getPackageName());
        Glide.with(holder.itemView.getContext()).load(imageResourceId).into(holder.imageView);
        holder.tv_ten.setText(gioHang.getTenGH());
        holder.tv_gia.setText(String.valueOf(gioHang.getGiaGH()));
        holder.tv_soluong.setText(String.valueOf(gioHang.getSoluongGH()));
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase.getDatabase(context).gioHangDao().deleteMonAn(gioHang);
                list.remove(gioHang);
               notifyDataSetChanged();

                Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tv_ten,tv_gia,tv_soluong;
        private Button btn_delete;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view4);
            tv_ten = itemView.findViewById(R.id.tv_tenmonan4);
            tv_gia = itemView.findViewById(R.id.tv_giamonan4);
            tv_soluong = itemView.findViewById(R.id.tv_soluong2);
            btn_delete = itemView.findViewById(R.id.btn_xoa);
        }
    }
}
