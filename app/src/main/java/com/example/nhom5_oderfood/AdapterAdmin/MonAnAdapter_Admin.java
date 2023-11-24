package com.example.nhom5_oderfood.AdapterAdmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DAO.TheloaiDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.Theloai;
import com.example.nhom5_oderfood.FragmentAdmin.DetailActivity_Admin;
import com.example.nhom5_oderfood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MonAnAdapter_Admin extends RecyclerView.Adapter<MonAnAdapter_Admin.ViewHolder> {
    private Context context;
    private ArrayList<MonAn> monAnList;


    public MonAnAdapter_Admin(Context context, ArrayList<MonAn> monAnList) {
        this.context = context;
        this.monAnList = monAnList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_monan_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn monAn = monAnList.get(position);
        holder.txt_tenma.setText(monAn.getTenMA());
        holder.txt_dongia.setText(String.valueOf(monAn.getGiaMA()));

//        String img = monAn.getHinhMA();
//        Picasso.get().load(img).into(holder.ImgAnh);
    }

    @Override
    public int getItemCount() {
        return monAnList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tenma, txt_dongia;
        ImageView btnUpdate, btnDelete, ImgAnh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tenma = itemView.findViewById(R.id.txt_tenmonan);
            txt_dongia = itemView.findViewById(R.id.txt_dongia);
            btnUpdate = itemView.findViewById(R.id.btnUpdate_ma);
            btnDelete = itemView.findViewById(R.id.btnDelete_ma);
            ImgAnh = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    MonAn monAn = monAnList.get(position);

                    // Tạo Intent để chuyển đến DetailActivity
                    Intent intent = new Intent(context, DetailActivity_Admin.class);

                    // Đính kèm dữ liệu món ăn cần hiển thị chi tiết
                    intent.putExtra("tenMonan", monAn.getTenMA());
                    intent.putExtra("donGia", monAn.getGiaMA());
                    intent.putExtra("moTa", monAn.getMotaMA());

                    // Khởi chạy Activity mới
                    context.startActivity(intent);
                }
            });
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có muốn xóa món ăn ?")
                            .setTitle("Xóa sản phẩm")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int position = getAdapterPosition();
                                    MonAn monan = monAnList.get(position);
                                    MonAnDAO monanDAO = new MonAnDAO(context);
                                    int result = monanDAO.deleteMonAn(monan);
                                    if (result > 0) {
                                        Toast.makeText(context, "Đã xóa món ăn", Toast.LENGTH_SHORT).show();
                                        monAnList.remove(position); // Xóa khỏi danh sách
                                        notifyItemRemoved(position); // Cập nhật giao diện
                                    } else {
                                        Toast.makeText(context, "Không xóa được món ăn", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View dialogView = LayoutInflater.from(context).inflate(R.layout.item_update_admin, null);
                    builder.setView(dialogView);
                    AlertDialog dialog = builder.create();

                    // Lấy các view trong layout item_update
                    EditText edtTenMonAn = dialogView.findViewById(R.id.edt_tenmonan_ud);
                    EditText edtGiaTien = dialogView.findViewById(R.id.edt_giatien_ud);
                    EditText edtMoTa = dialogView.findViewById(R.id.edt_mota_ud);
                    Button btnLuu = dialogView.findViewById(R.id.btnud_monan);
                    Spinner spinner = dialogView.findViewById(R.id.spinnertl);

                    TheloaiDAO theloaiDAO = new TheloaiDAO(context);
                    ArrayList<Theloai> listTheloai = theloaiDAO.getAllTheLoai();
                    SpinnerTheloai spinnerTheloai = new SpinnerTheloai(context, listTheloai);
                    spinner.setAdapter(spinnerTheloai);

                    // Lấy thông tin món ăn được chọn
                    int position = getAdapterPosition();
                    MonAn selectedMonAn = monAnList.get(position);

                    // Thiết lập dữ liệu hiện tại của món ăn vào các view
                    edtTenMonAn.setText(selectedMonAn.getTenMA());
                    edtGiaTien.setText(String.valueOf(selectedMonAn.getGiaMA()));
                    edtMoTa.setText(selectedMonAn.getMotaMA());

                    // Xử lý sự kiện khi người dùng nhấn nút "Lưu"
                    btnLuu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Lấy thông tin đã chỉnh sửa từ các view
                            String tenMonAn = edtTenMonAn.getText().toString();
                            int giaTien = Integer.parseInt(edtGiaTien.getText().toString());
                            String moTa = edtMoTa.getText().toString();
                            Theloai selectedTheloai = (Theloai) spinner.getSelectedItem(); // Lấy đối tượng Theloai đã chọn từ Spinner

                            // Cập nhật thông tin món ăn trong CSDL
                            selectedMonAn.setTenMA(tenMonAn);
                            selectedMonAn.setGiaMA(giaTien);
                            selectedMonAn.setMotaMA(moTa);
                            selectedMonAn.setLoaiMA(Integer.parseInt(selectedTheloai.getMaTL())); // Cập nhật đối tượng Theloai cho món ăn
                            MonAnDAO monAnDAO = new MonAnDAO(context);
                            monAnDAO.updateMonAn(selectedMonAn);

                            // Cập nhật danh sách và thông báo thành công
                            monAnList.set(position, selectedMonAn);
                            notifyItemChanged(position);
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });
        }
    }
}
