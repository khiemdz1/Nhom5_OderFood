package com.example.nhom5_oderfood.FragmentKhachHang;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom5_oderfood.Adapter.NhaCungCapAdapter;
import com.example.nhom5_oderfood.DAO.NhaCungCapDAO;
import com.example.nhom5_oderfood.DTO.NhaCungCap;
import com.example.nhom5_oderfood.R;

import com.example.nhom5_oderfood.databinding.FragNhacungcapBinding;

import java.util.ArrayList;

public class Frag_NhaCungCap extends Fragment {
    NhaCungCapDAO nhaCungCapDAO;
    ArrayList<NhaCungCap> arrayList;
    NhaCungCapAdapter adapter;
    FragNhacungcapBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragNhacungcapBinding.inflate(inflater, container, false);
        nhaCungCapDAO = new NhaCungCapDAO(getContext());
        loatData();
        return binding.getRoot();
    }
    public void loatData(){

        arrayList = nhaCungCapDAO.getDSNCC();
        adapter = new NhaCungCapAdapter(getContext() , arrayList);
        binding.rcvNcc.setAdapter(adapter);
    }
}
