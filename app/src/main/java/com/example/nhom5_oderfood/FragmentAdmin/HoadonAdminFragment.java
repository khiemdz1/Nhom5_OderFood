package com.example.nhom5_oderfood.FragmentAdmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom5_oderfood.AdapterAdmin.HoaDonAdminAdapter;
import com.example.nhom5_oderfood.DAO.HoaDonDAO;
import com.example.nhom5_oderfood.DTO.HoaDon;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;

public class HoadonAdminFragment extends Fragment {
    RecyclerView recyclerView;
    HoaDonDAO hoaDonDAO;
    HoaDonAdminAdapter hoaDonAdapter;
    ArrayList<HoaDon> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoadon_admin, container, false);

        recyclerView = view.findViewById(R.id.rcv_hoaddonadmin);
        hoaDonDAO = new HoaDonDAO(getContext());
        list = hoaDonDAO.getAllHoaDon();
        hoaDonAdapter = new HoaDonAdminAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hoaDonAdapter);

        return view;
    }
}