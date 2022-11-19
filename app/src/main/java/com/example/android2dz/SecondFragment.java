package com.example.android2dz;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    private ImageView imageView;
    private TextView tvText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInst1anceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        getData();
        click();
    }

    private void click() {
        tvText.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_right_to_left,R.anim.enter_left_to_right)
                    .add(R.id.fragment_container, ThirdFragment.class, bundle)
                    .addToBackStack("SecondFragment")
                    .commit();
        });
    }

    private void initialize(View view) {
        imageView = view.findViewById(R.id.im_2);
        tvText = view.findViewById(R.id.text_2);
    }

    private void getData() {
        Bundle data = getArguments();
        if (data != null) {
            String text = data.getString(FirstFragment.TEXT_BUNDLE_KEY);
            Uri imageForGet = Uri.parse(data.getString(FirstFragment.IMAGE_BUNDLE_KEY));
            tvText.setText(text);
            imageView.setImageURI(imageForGet);
        }
    }

}