package com.example.nhom5_oderfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.DTO.NhaCungCap;
import com.example.nhom5_oderfood.databinding.ItemNhacungcapBinding;

import java.util.ArrayList;


public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.ViewHolder> {

    private Context context;

    public ArrayList<NhaCungCap> arrayList;

    public NhaCungCapAdapter(Context context, ArrayList<NhaCungCap> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNhacungcapBinding binding = ItemNhacungcapBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NhaCungCap nhaCungCap = arrayList.get(position);

        holder.binding.tenNCC.setText(nhaCungCap.getTenNhaCC());
        holder.binding.thongTin.setText(nhaCungCap.getThongTin());
        holder.binding.lienHe.setText(nhaCungCap.getLienHe());
        holder.binding.Email.setText(nhaCungCap.getEmail());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemNhacungcapBinding binding;
        public ViewHolder(@NonNull ItemNhacungcapBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
