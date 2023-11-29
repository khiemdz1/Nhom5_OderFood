package com.example.nhom5_oderfood.FragmentKhachHang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.nhom5_oderfood.databinding.FragFeedbackBinding;
import com.example.nhom5_oderfood.databinding.FragNhacungcapBinding;


public class Frag_FeedBack extends Fragment {

    FragFeedbackBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragFeedbackBinding.inflate(inflater, container, false);

        listener();
        return binding.getRoot();
    }
    private void listener(){
        binding.feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //xây dựng URI (Uniform Resource Identifier) cho đối tượng Intent.
                //URI "mailto" với địa chỉ email, chủ đề là "Feedback"
                String UriText = "mailto:" + Uri.encode("quangnd1307@gmail.com") +"?subject="
                        + Uri.encode("Feedback") + Uri.encode("");
                Uri uri = Uri.parse(UriText);
                intent.setData(uri);
                startActivity(Intent.createChooser(intent,"send email"));
            }
        });
    }
}
