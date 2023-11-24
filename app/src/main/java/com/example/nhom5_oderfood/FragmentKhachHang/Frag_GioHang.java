package com.example.nhom5_oderfood.FragmentKhachHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.Adapter.GioHangAdapter;
import com.example.nhom5_oderfood.DTO.GioHang;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.AppDatabase;
import com.example.nhom5_oderfood.FragmentKhachHang.databasegiohang.GioHangDAO;
import com.example.nhom5_oderfood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class Frag_GioHang extends Fragment {
     GioHangAdapter gioHangAdapter;
     RecyclerView recyclerView;
     List<GioHang> gioHangList;
     TextView tv_tonggia;
     Button btn_dathang;
     GioHangDAO gioHangDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_giohang, container, false);
         recyclerView = view.findViewById(R.id.rcv_view2);
         gioHangAdapter = new GioHangAdapter();
         gioHangList = new ArrayList<>();
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
         recyclerView.setLayoutManager(linearLayoutManager);
         recyclerView.setAdapter(gioHangAdapter);
         loadDataFromDatabase();

        tv_tonggia = view.findViewById(R.id.tv_tonggia);
        int totalPrice = gioHangAdapter.calculateTotalPrice();
        tv_tonggia.setText(String.valueOf(totalPrice));

        btn_dathang = view.findViewById(R.id.btn_dathang);
        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gioHangDAO = AppDatabase.getDatabase(getContext()).gioHangDao();
                List<GioHang> list = gioHangDAO.getlitsMonan();
                if (list.isEmpty()) {
                    Snackbar.make(view,"Giỏ hàng trống, vui lòng thêm sản phẩm trước khi đặt hàng",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                BottomSheetDialogDatHang();
            }
        });

        return view;
    }

    private void BottomSheetDialogDatHang() {
        String gia = tv_tonggia.getText().toString();
        int gia2 = Integer.parseInt(gia);
        MyBottomSheetFragDatHang myBottomSheetFragDatHang = new  MyBottomSheetFragDatHang();
        Bundle bundle = new Bundle();
        bundle.putInt("giatong", gia2);
        myBottomSheetFragDatHang.setArguments(bundle);
        myBottomSheetFragDatHang.setCancelable(false);
        myBottomSheetFragDatHang.show(getActivity().getSupportFragmentManager(), myBottomSheetFragDatHang.getTag());
    }


    private void loadDataFromDatabase() {
        AppDatabase db = AppDatabase.getDatabase(getContext());
        List<GioHang> gioHangList = db.gioHangDao().getlitsMonan();
        gioHangAdapter.setData(getContext(),gioHangList);
    }
    @Override
    public void onResume() {
        super.onResume();
        loadDataFromDatabase();
    }

}
