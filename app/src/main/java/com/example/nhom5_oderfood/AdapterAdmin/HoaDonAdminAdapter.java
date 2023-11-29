package com.example.nhom5_oderfood.AdapterAdmin;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.DAO.HoaDonDAO;
import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class HoaDonAdminAdapter extends RecyclerView.Adapter<HoaDonAdminAdapter.ViewHolder> {
    Context context;
    ArrayList<HoaDon> list;
    HoaDonDAO hoaDonDAO;

    public HoaDonAdminAdapter(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        hoaDonDAO = new HoaDonDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoadon_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon hoaDon = list.get(position);
        if (hoaDon != null) {
            holder.maHD.setText(String.valueOf(hoaDon.getMaHD()));
            holder.ten.setText(hoaDon.getTenkhachhangHD());
            holder.sdt.setText(hoaDon.getSdtkhachhangHD());
            holder.diachi.setText(hoaDon.getDiachikhachhangHD());
            holder.monan.setText(hoaDon.getTenmonanHD());
            holder.soluong.setText(String.valueOf(hoaDon.getSoluongHD()));
            holder.ngaydat.setText(String.valueOf(hoaDon.getNgaydatHD()));
            holder.tongtien.setText(String.format("%,d", hoaDon.getGiaHD()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maHD, ten, sdt, diachi, monan, soluong, tongtien, ngaydat;
        Button btnXoaHoaDon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maHD = itemView.findViewById(R.id.tv_maHD);
            ten = itemView.findViewById(R.id.tv_tenHD);
            sdt = itemView.findViewById(R.id.tv_sdtHD);
            diachi = itemView.findViewById(R.id.tv_diachiHD);
            monan = itemView.findViewById(R.id.tv_tenmonanHD);
            soluong = itemView.findViewById(R.id.tv_soluongHD);
            ngaydat = itemView.findViewById(R.id.tv_ngaydatHD);
            tongtien = itemView.findViewById(R.id.tv_tongtienHD);

            btnXoaHoaDon = itemView.findViewById(R.id.btnxoahoadon);

            btnXoaHoaDon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa hóa đơn");
                    builder.setMessage("Bạn có chắc chắn muốn xóa hóa đơn này?");

                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HoaDon position = list.get(getAdapterPosition());
                            hoaDonDAO = new HoaDonDAO(context);
                            int result = hoaDonDAO.DeleteHoaDon(position);
                            if (result > 0) {
                                Toast.makeText(context, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(hoaDonDAO.getAllHoaDon());
                                notifyDataSetChanged();
                                dialog.dismiss();

                            } else {
                                Toast.makeText(context, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.dismiss();
                    dialog.show();
                }
            });
        }
    }
}
