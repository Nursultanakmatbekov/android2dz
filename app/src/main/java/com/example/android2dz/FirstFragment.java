package com.example.android2dz;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    public static final String TEXT_BUNDLE_KEY = "text";
    public static final String IMAGE_BUNDLE_KEY = "image";
    private ImageView imageView;
    private EditText enterNumber;
    private Button btnButton;
    private Uri imageForSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.im_1);
        enterNumber = view.findViewById(R.id.text_1);
        btnButton = view.findViewById(R.id.button_1);
        setUpListeners();
    }

    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            @Nullable Intent data = result.getData();
            if (data != null) {
                @Nullable Uri imageFranGallery = data.getData();
                if (imageFranGallery != null) {
                    imageView.setImageURI(imageFranGallery);
                    imageForSend = imageFranGallery;
                }
            }
        }
    });

    private void setUpListeners() {
        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intent);
        });
        btnButton.setOnClickListener(view -> {
            String title = enterNumber.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString(TEXT_BUNDLE_KEY, title);

            if (title.isEmpty() && imageForSend == null) {
                Toast.makeText(getActivity(), "Введите текст и выберите изображение!", LENGTH_SHORT).show();
            } else if (imageForSend == null) {
                Toast.makeText(getActivity(), "Выберите изображение", LENGTH_SHORT).show();
            } else if (title.isEmpty()) {
                Toast.makeText(getActivity(), "Введите текст", LENGTH_SHORT).show();
            } else {

                bundle.putString(IMAGE_BUNDLE_KEY, imageForSend.toString());
                FirstFragment.this.getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_to_left,R.anim.enter_left_to_right)
                        .add(R.id.fragment_container, SecondFragment.class, bundle)
                        .addToBackStack("FirstFragment")
                        .commit();
            }
        });
    }

}


