package com.example.nhom5_oderfood.FragmentKhachHang;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.nhom5_oderfood.DTO.GioHang;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.AppDatabase;
import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

public class MyBottomSheetFrag extends BottomSheetDialogFragment{
    MonAn monAn;
    ImageView imageView;
    TextView tv_ten, tv_gia,tv_soluong;
    Button btn_huy,btn_tru,btn_cong,btn_them;
    int dem = 1;
    String hinhAnh,tenMonAn;
    int donGia;
    int giamoi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //lấy dữ liệu bằng bundle
        Bundle bundleReceive = getArguments();
        if (bundleReceive != null) {
            hinhAnh = bundleReceive.getString("Hinhanh");
            tenMonAn = bundleReceive.getString("Tenmonan");
            donGia = bundleReceive.getInt("Donngia");
            monAn = new MonAn(hinhAnh,tenMonAn,donGia);

        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //lấy layout
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_themvaogiohang, null);
        bottomSheetDialog.setContentView(view);
        //ánh xạ
        imageView = view.findViewById(R.id.image_view3);
        tv_ten = view.findViewById(R.id.tv_tenmonan3);
        tv_gia = view.findViewById(R.id.tv_giamonan3);
        tv_soluong = view.findViewById(R.id.tv_soluong);
        btn_huy = view.findViewById(R.id.btn_cancel);
        btn_them = view.findViewById(R.id.btn_them2);
        btn_tru = view.findViewById(R.id.btn_tru);
        btn_cong = view.findViewById(R.id.btn_cong);
        // set dữ liệu lên buttomsheetdialog
        if (monAn != null) {
            Glide.with(this)
                    .load(hinhAnh)
                    .into(imageView);
            tv_ten.setText(monAn.getTenMA());
            tv_gia.setText(String.format("%,d",monAn.getGiaMA()));
        }
        btn_huy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });

        btn_cong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               dem++;
               updateUI();
            }
        });
        btn_tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dem > 1) {
                    dem--;
                    updateUI();
                } else {
                   Snackbar.make(view,"Error Data",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        updateUI();
        AppDatabase db = AppDatabase.getDatabase(getContext());
        int count = db.gioHangDao().checkMonan(tenMonAn);
        if(count != 0 ){
            btn_them.setEnabled(false);
            btn_them.setText("Đã Có Trong Giỏ Hàng");
            btn_them.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            btn_them.setBackgroundResource(R.drawable.custom_cancel);
        }
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            AppDatabase db = AppDatabase.getDatabase(getContext());
                            // Món ăn chưa tồn tại, thực hiện chèn
                            GioHang gioHang = new GioHang();
                            gioHang.setHinhanhGH(hinhAnh);
                            gioHang.setTenGH(tenMonAn);
                            gioHang.setGiaGH(giamoi);
                            gioHang.setSoluongGH(dem);
                            db.gioHangDao().insertMonan(gioHang);
                            Toast.makeText(getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                    }
                });
        return bottomSheetDialog;
    }

    private void updateUI() {
        tv_soluong.setText(String.valueOf(dem));
         giamoi = dem * donGia;
        tv_gia.setText(String.format("%,d",giamoi));
    }

}
