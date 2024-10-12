package com.example.myapplication.ui.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentEditBinding;

public class EditFragment extends Fragment {

    private FragmentEditBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        com.example.myapplication.ui.edit.EditViewModel editViewModel =
                new ViewModelProvider(this).get(EditViewModel.class);

        binding = FragmentEditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEdit;
        editViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}