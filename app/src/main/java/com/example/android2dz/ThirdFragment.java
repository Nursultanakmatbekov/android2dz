package com.example.android2dz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ThirdFragment extends Fragment {
    EditText edName, edPassword;
    Button buttonTwo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        setUpListeners();
    }

    private void initialize(View view) {
        buttonTwo = view.findViewById(R.id.buttonTwo);
        edName = view.findViewById(R.id.edOne);
        edPassword = view.findViewById(R.id.edTwo);
    }

    private void setUpListeners() {
        buttonTwo.setOnClickListener(view -> {
            String name = edName.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            if (!name.equals("admin") && !password.equals("admin")) {
                edName.setError("error");
                edPassword.setError("error");
            } else if (!name.equals("admin")) {
                edName.setError("error");
            } else if (!password.equals("admin")) {
                edPassword.setError("error");
            } else {
                Bundle bundle1 = new Bundle();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_to_left,R.anim.enter_left_to_right)
                        .replace(R.id.fragment_container, FirstFragment.class, bundle1)
                        .commit();

            }
        });
    }

}

