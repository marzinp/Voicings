package com.example.Voicings.ui.choix;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.Voicings.DBHelper;
import com.example.Voicings.R;
import com.example.Voicings.databinding.FragmentChoixBinding;

import java.util.ArrayList;

public class ChoixFragment extends Fragment implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private FragmentChoixBinding binding;
    String selectedType, selectedMelody, selectedStyle;
    private CheckBox cbType, cbMelody, cbStyle;
    private Boolean cbTypeChecked = Boolean.TRUE;
    private Boolean cbMelodyChecked = Boolean.FALSE;
    private Boolean cbStyleChecked = Boolean.FALSE;
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
        cbType = this.getView().findViewById(R.id.checkType);
        cbType.setOnCheckedChangeListener(this);
        cbMelody = this.getView().findViewById(R.id.checkMelody);
        cbMelody.setOnCheckedChangeListener(this);
        cbStyle = this.getView().findViewById(R.id.checkStyle);
        cbStyle.setOnCheckedChangeListener(this);
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
        selectedType = spinner.getSelectedItem().toString();
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
        spinner.setOnItemSelectedListener(this);
        selectedMelody = spinner.getSelectedItem().toString();
        //Style Spinner
        results = new ArrayList<String>();
        spinner = view.findViewById(R.id.StyleSpinner);
        //selectedStyle = spinner.getSelectedItem().toString();
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
        spinner.setOnItemSelectedListener(this);
        selectedStyle = spinner.getSelectedItem().toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton chk, boolean b) {
        cbTypeChecked = cbType.isChecked();
        cbMelodyChecked = cbMelody.isChecked();
        cbStyleChecked = cbStyle.isChecked();
        this.displayResults();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Spinner spinner;
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        //if(view == this.getView().findViewById(R.id.TypeSpinner)) {
        spinner = this.getView().findViewById(R.id.TypeSpinner);
        selectedType = spinner.getSelectedItem().toString();
        //}
        // else if(view==this.getView().findViewById(R.id.MelodySpinner)) {
        spinner = this.getView().findViewById(R.id.MelodySpinner);
        selectedMelody = spinner.getSelectedItem().toString();
        //}
        //else if(view==this.getView().findViewById(R.id.StyleSpinner)) {
        spinner = this.getView().findViewById(R.id.StyleSpinner);
        selectedStyle = spinner.getSelectedItem().toString();
        // }
        this.displayResults();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        int i = 2;
    }

    public void displayResults() {
        TextView textView = binding.displayChordsText;
        ArrayList<String> results = new ArrayList<String>();
        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase newDB = dbHelper.getReadableDatabase();
        int nbChecked = 0;
        Cursor c;
        String MY_QUERY = "SELECT * FROM Voicings";
        c = newDB.rawQuery(MY_QUERY, null);
        String[] condition = null;
        if (cbTypeChecked) {
            MY_QUERY += " WHERE type=?";
            condition = new String[]{selectedType};
            nbChecked++;
        }
        if (cbMelodyChecked) {
            if (nbChecked == 0) {
                MY_QUERY += " WHERE melody=?";
                condition = new String[]{selectedMelody};

            } else {
                MY_QUERY += " AND melody=?";
                condition = new String[]{selectedType, selectedMelody};
            }
            nbChecked++;
        }
        if (cbStyleChecked) {
            if (nbChecked == 0) {
                MY_QUERY += " WHERE Style=?";
                condition = new String[]{selectedStyle};

            } else if (nbChecked == 1) {
                MY_QUERY += " AND style=?";
                if (cbMelodyChecked) {
                    condition = new String[]{selectedMelody, selectedStyle};
                } else condition = new String[]{selectedType, selectedStyle};
            } else if (nbChecked == 2) {
                MY_QUERY += " AND style=?";
                condition = new String[]{selectedType, selectedMelody, selectedStyle};
            }
            nbChecked++;

        }
        if (nbChecked > 0) c = newDB.rawQuery(MY_QUERY, condition);
        //Now iterate with cursor
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

}