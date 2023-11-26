package com.example.nhom5_oderfood.FragmentKhachHang;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.Adapter.HoaDonAdapter;
import com.example.nhom5_oderfood.DAO.HoaDonDAO;
import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class Frag_HoaDon extends Fragment {
    RecyclerView recyclerView;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDon> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_hoadon, container, false);
        recyclerView = view.findViewById(R.id.rcv_view3);
        hoaDonDAO = new HoaDonDAO(getContext());
        int loggedInUserId = getLoggedInUserId();
        list = hoaDonDAO.getAllByCustomerId(loggedInUserId);
        hoaDonAdapter = new HoaDonAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hoaDonAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return view;
    }

    private int getLoggedInUserId() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        return sharedPreferences.getInt("USER_ID", -1); // -1 là giá trị mặc định nếu không tìm thấy
    }
}
