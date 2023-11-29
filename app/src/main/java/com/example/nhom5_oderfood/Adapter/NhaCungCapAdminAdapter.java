package com.example.nhom5_oderfood.Adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DAO.NhaCungCapDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.DTO.NhaCungCap;

import com.example.nhom5_oderfood.R;
import com.example.nhom5_oderfood.databinding.DiaglogChucnangBinding;
import com.example.nhom5_oderfood.databinding.ItemNhacungcapadminBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhaCungCapAdminAdapter extends RecyclerView.Adapter<NhaCungCapAdminAdapter.ViewHolder> {

    private Context context;
    public ArrayList<NhaCungCap> arrayList;


    public NhaCungCapAdminAdapter(Context context, ArrayList<NhaCungCap> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNhacungcapadminBinding binding = ItemNhacungcapadminBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        NhaCungCap nhaCungCap = arrayList.get(position);

        holder.binding.tenNCC.setText(nhaCungCap.getTenNhaCC());
        holder.binding.thongTin.setText(nhaCungCap.getThongTin());
        holder.binding.lienHe.setText(nhaCungCap.getLienHe());
        holder.binding.Email.setText(nhaCungCap.getEmail());
        holder.binding.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(arrayList.get(holder.getAdapterPosition()).getMaNCC());
            }
        });
        holder.binding.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate(arrayList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemNhacungcapadminBinding binding;
        public ViewHolder(@NonNull ItemNhacungcapadminBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public void delete(int id) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("Bạn có chắc muốn xóa không ?");
        builder.setPositiveButton("chắc chắn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO(context);
                nhaCungCapDAO.deleteNCC(id);
                Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                arrayList.clear();
                arrayList = nhaCungCapDAO.getDSNCC();
                notifyDataSetChanged();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Bạn chọn không xóa", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();

            }
        });
        builder.show();


    }
    private void showDialogUpdate(NhaCungCap nhaCungCap){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater  = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogupdate_ncc,null);


        TextInputEditText ten = view.findViewById(R.id.updateten);
        TextInputEditText thongtin = view.findViewById(R.id.updateTT);
        TextInputEditText lienhe = view.findViewById(R.id.updatelienhe);
        TextInputEditText email = view.findViewById(R.id.updateemail);
        Button update = view.findViewById(R.id.btnSUA);

        ten.setText(nhaCungCap.getTenNhaCC());
        thongtin.setText(nhaCungCap.getThongTin());
        lienhe.setText(nhaCungCap.getLienHe());
        email.setText(nhaCungCap.getEmail());

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenncc = ten.getText().toString();
                String Thongtin = thongtin.getText().toString();
                String Lienhe = lienhe.getText().toString();
                String Email = email.getText().toString();

                nhaCungCap.setTenNhaCC(ten.getText().toString());
                nhaCungCap.setThongTin(thongtin.getText().toString());
                nhaCungCap.setLienHe(lienhe.getText().toString());
                nhaCungCap.setEmail(email.getText().toString());

                    NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO(context);
                    boolean check = nhaCungCapDAO.updateNCC(nhaCungCap);

                if (ten.length() == 0 || thongtin.length() == 0 || lienhe.length() == 0 || email.length() == 0) {
                    Toast.makeText(context, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (!validatePhoneNumber(Lienhe)) {
                    lienhe.setError("Vui lòng nhập đúng định dạng");
                } else if (!validateEmail(Email)) {
                    email.setError("Vui lòng nhập đúng định dạng");
                } else {
                    if (check) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        arrayList.clear();
                        arrayList = nhaCungCapDAO.getDSNCC();
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                }

        });

}
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean validateEmail(final String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    private static final String PHONE_NUMBER_PATTERN =
            "^(\\+\\d{1,3}[- ]?)?\\(?\\d{1,6}\\)?[-.\\s]?\\d{1,15}$";

    private static final Pattern pattern1 = Pattern.compile(PHONE_NUMBER_PATTERN);

    public static boolean validatePhoneNumber(String phoneNumber) {
        Matcher matcher = pattern1.matcher(phoneNumber);
        return matcher.matches();

    }
}
