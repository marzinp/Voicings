package com.example.myapplication.ui.choix;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.DBHelper;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentChoixBinding;

import java.util.ArrayList;

public class ChoixFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentChoixBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChoixViewModel choixViewModel =
                new ViewModelProvider(this).get(ChoixViewModel.class);

        binding = FragmentChoixBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.displayChordsText;
        choixViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = view.findViewById(R.id.TypeSpinner);
        ArrayList<String> results = new ArrayList<String>();
        DBHelper dbHelper = new DBHelper(this.getContext());
        SQLiteDatabase newDB = dbHelper.getWritableDatabase();
        //Type Spinner
        String YOUR_QUERY = "SELECT * FROM Types";
        Cursor c = newDB.rawQuery(YOUR_QUERY, null);//newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")));
                } while (c.moveToNext());
            }
        }
        TextView text = view.findViewById(R.id.typeSpinnerLabel);
        text.setText("Chord Types");
// Specify the layout to use when the list of choices appears.
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_center_item, results);
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Melody Spinner
        results = new ArrayList<String>();
        spinner = view.findViewById(R.id.MelodySpinner);
        YOUR_QUERY = "SELECT * FROM Melodys";
        c = newDB.rawQuery(YOUR_QUERY, null);//newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")));
                } while (c.moveToNext());
            }
        }
        // Specify the layout to use when the list of choices appears.
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_center_item, results);
        text = view.findViewById(R.id.melodySpinnerLabel);
        text.setText("Melody note");
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        //Style Spinner
        results = new ArrayList<String>();
        spinner = view.findViewById(R.id.styleSpinner);
        YOUR_QUERY = "SELECT * FROM Styles";
        c = newDB.rawQuery(YOUR_QUERY, null);//newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")));
                } while (c.moveToNext());
            }
        }
        // Specify the layout to use when the list of choices appears.
        adapter = new ArrayAdapter<String>(this.getContext(), R.layout.spinner_center_item, results);
        text = view.findViewById(R.id.styleSpinnerLabel);
        text.setText("Chord Style");
// Apply the adapter to the spinner.
        spinner.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        TextView textView = binding.displayChordsText;
        ArrayList<String> results = new ArrayList<String>();
        String text = parent.getItemAtPosition(pos).toString();
        String YOUR_QUERY = "SELECT * FROM Voicings WHERE type=" + text;
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase newDB = dbHelper.getReadableDatabase();
        Cursor c = newDB.rawQuery("SELECT * FROM voicings WHERE type=?", new String[]{text});//Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Type"))
                            + " " + c.getString(c.getColumnIndexOrThrow("Melody"))
                            + " " + c.getString(c.getColumnIndexOrThrow("Style")));
                } while (c.moveToNext());
            }
        }
        textView.setText(results.toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        int i = 2;
    }
}