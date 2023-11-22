package com.example.nhom5_oderfood.FragmentKhachHang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nhom5_oderfood.Adapter.MonAnAdapter;
import com.example.nhom5_oderfood.DAO.MonAnDAO;
import com.example.nhom5_oderfood.DTO.MonAn;
import com.example.nhom5_oderfood.Interface.ItemClickListener;
import com.example.nhom5_oderfood.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_Home extends Fragment {

    ImageSlider imageSlider;
    RecyclerView rcv;
    ArrayList<MonAn> list , list2;
    MonAnAdapter monAnAdapter;
    androidx.appcompat.widget.SearchView searchView;
    MonAnDAO monAnDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getContext();
        View view =  inflater.inflate(R.layout.frag_home,container,false);
         imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT);
        searchView = view.findViewById(R.id.search);
        imageSlider.startSliding(4000);
        rcv = view.findViewById(R.id.rcv_view);
        monAnDAO = new MonAnDAO(getContext());
        list = monAnDAO.getAll();
        list2 = monAnDAO.getAll();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        rcv.setLayoutManager(gridLayoutManager);


        monAnAdapter = new MonAnAdapter(getContext(),list);
        rcv.setAdapter(monAnAdapter);

        monAnAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onclick(int position) {
                MonAn monAn = list.get(position);
                Intent intent = new Intent(context, ThongTinMonAn.class);
                intent.putExtra("Hinhanh",monAn.getHinhMA());
                intent.putExtra("Tenmonan",monAn.getTenMA());
                intent.putExtra("Donngia",monAn.getGiaMA());
                intent.putExtra("Mota",monAn.getMotaMA());
                startActivity(intent);
            }
        });
        return view;
    }
    public void filter(String s){
        list.clear();
        for (MonAn ma:list2){
            if (ma.getTenMA().contains(s.toString())){
                list.add(ma);
            }
        }
        monAnAdapter.notifyDataSetChanged();
    }
}
