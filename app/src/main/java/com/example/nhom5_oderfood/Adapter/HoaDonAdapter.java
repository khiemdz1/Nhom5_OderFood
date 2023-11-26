package com.example.nhom5_oderfood.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.DAO.HoaDonDAO;
import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;
import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHodel>{
    Context context;
    ArrayList<HoaDon> list;
    HoaDonDAO hoaDonDAO;
    public HoaDonAdapter(Context context ,ArrayList<HoaDon> list){
        this.context = context;
        this.list = list;
        hoaDonDAO = new HoaDonDAO(context);
    }
    @NonNull
    @Override
    public ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoadon,parent,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodel holder, int position) {
        HoaDon hoaDon = list.get(position);
        if(hoaDon != null){
            holder.maHD.setText(hoaDon.getMaHD());
            holder.ten.setText(hoaDon.getTenkhachhangHD());
            holder.sdt.setText(hoaDon.getSdtkhachhangHD());
            holder.diachi.setText(hoaDon.getDiachikhachhangHD());
            holder.monan.setText(hoaDon.getTenmonanHD());
            holder.soluong.setText(String.valueOf(hoaDon.getSoluongHD()));
            holder.ngaydat.setText(hoaDon.getNgaydatHD());
            holder.tongtien.setText(String.format("%,d",hoaDon.getGiaHD()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView maHD,ten,sdt,diachi,monan,soluong,tongtien,ngaydat;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
          maHD = itemView.findViewById(R.id.tv_maHD);
          ten = itemView.findViewById(R.id.tv_tenHD);
          sdt = itemView.findViewById(R.id.tv_sdtHD);
          diachi = itemView.findViewById(R.id.tv_diachiHD);
          monan = itemView.findViewById(R.id.tv_tenmonanHD);
          soluong = itemView.findViewById(R.id.tv_soluongHD);
          ngaydat = itemView.findViewById(R.id.tv_ngaydatHD);
          tongtien = itemView.findViewById(R.id.tv_tongtienHD);
        }
    }
}
