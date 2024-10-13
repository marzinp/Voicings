package com.example.Voicings.ui.choix

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Voicings.DBHelper
import com.example.Voicings.R
import com.example.Voicings.databinding.FragmentChoixBinding


class ChoixFragment : Fragment(), AdapterView.OnItemSelectedListener,
    CompoundButton.OnCheckedChangeListener {
    private var binding: FragmentChoixBinding? = null
    var selectedType: String? = null
    var selectedMelody: String? = null
    var selectedStyle: String? = null
    private var cbType: CheckBox? = null
    private var cbMelody: CheckBox? = null
    private var cbStyle: CheckBox? = null
    private var typeSpinner: Spinner? = null
    private var melodySpinner: Spinner? = null
    private var styleSpinner: Spinner? = null
    private var cbTypeChecked: Boolean? = null
    private var cbMelodyChecked: Boolean? = null
    private var cbStyleChecked: Boolean? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val choixViewModel =
            ViewModelProvider(this).get(ChoixViewModel::class.java)

        binding = FragmentChoixBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding?.displayChordsList
        typeSpinner = binding?.TypeSpinner
        melodySpinner = binding?.MelodySpinner
        styleSpinner = binding?.StyleSpinner
        cbType = binding?.checkType
        cbTypeChecked = cbType?.isChecked
        cbMelody = binding?.checkMelody
        cbMelodyChecked = cbMelody?.isChecked
        cbStyle = binding?.checkStyle
        cbStyleChecked = cbMelody?.isChecked
        choixViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
            textView?.text = text
        }
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var spinner = typeSpinner
        var results = ArrayList<String?>()
        val dbHelper = DBHelper(this.context)
        val newDB = dbHelper.writableDatabase

        cbType?.setOnCheckedChangeListener(this)
        cbMelody?.setOnCheckedChangeListener(this)
        cbStyle?.setOnCheckedChangeListener(this)
        //Type Spinner
        var YOUR_QUERY = "SELECT * FROM Types"
        var c = newDB.rawQuery(
            YOUR_QUERY,
            null
        ) //newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")))
                } while (c.moveToNext())
            }
        }
        c.close()
        var text = view.findViewById<TextView>(R.id.typeSpinnerLabel)
        text.text = "Chord Types"
        // Specify the layout to use when the list of choices appears.
        var adapter: ArrayAdapter<*> =
            ArrayAdapter(this.requireContext(), R.layout.spinner_center_item, results)
        // Apply the adapter to the spinner.
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = this
        selectedType = spinner?.selectedItem.toString()
        //Melody Spinner
        results = ArrayList()
        spinner = melodySpinner
        YOUR_QUERY = "SELECT * FROM Melodys"
        c = newDB.rawQuery(
            YOUR_QUERY,
            null
        ) //newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")))
                } while (c.moveToNext())
            }
        }
        c.close()
        // Specify the layout to use when the list of choices appears.
        adapter = ArrayAdapter(this.requireContext(), R.layout.spinner_center_item, results)
        text = binding?.melodySpinnerLabel
        text.text = "Melody note"
        // Apply the adapter to the spinner.
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = this
        selectedMelody = spinner?.selectedItem.toString()
        //Style Spinner
        results = ArrayList()
        spinner = styleSpinner
        //selectedStyle = spinner.getSelectedItem().toString();
        YOUR_QUERY = "SELECT * FROM Styles"
        c = newDB.rawQuery(
            YOUR_QUERY,
            null
        ) //newDB.rawQuery(YOUR_QUERY,null);    //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(c.getString(c.getColumnIndexOrThrow("Name")))
                } while (c.moveToNext())
            }
        }
        // Specify the layout to use when the list of choices appears.
        adapter = ArrayAdapter(this.requireContext(), R.layout.spinner_center_item, results)
        text = binding?.styleSpinnerLabel
        text.text = "Chord Style"
        // Apply the adapter to the spinner.
        spinner?.textAlignment = View.TEXT_ALIGNMENT_CENTER
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = this
        selectedStyle = spinner?.selectedItem.toString()
        c.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCheckedChanged(chk: CompoundButton, b: Boolean) {
        cbTypeChecked = cbType!!.isChecked
        cbMelodyChecked = cbMelody!!.isChecked
        cbStyleChecked = cbStyle!!.isChecked
        this.displayResults()
    }


    override fun onItemSelected(
        parent: AdapterView<*>?, view: View,
        pos: Int, id: Long
    ) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        //if(view == this.getView().findViewById(R.id.TypeSpinner)) {
        var spinner = typeSpinner
        selectedType = spinner?.selectedItem.toString()
        //}
        // else if(view==this.getView().findViewById(R.id.MelodySpinner)) {
        spinner = melodySpinner
        selectedMelody = spinner?.selectedItem.toString()
        //}
        //else if(view==this.getView().findViewById(R.id.StyleSpinner)) {
        spinner = styleSpinner
        selectedStyle = spinner?.selectedItem.toString()
        // }
        this.displayResults()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    fun displayResults() {
        val textView = binding?.displayChordsList
        val results = ArrayList<String>()
        val dbHelper = DBHelper(context)
        val newDB = dbHelper.readableDatabase
        var nbChecked = 0
        var c: Cursor?
        var MY_QUERY = "SELECT * FROM Voicings"
        c = newDB.rawQuery(MY_QUERY, null)
        var condition: Array<String?>? = null
        if (cbTypeChecked == true) {
            MY_QUERY += " WHERE type=?"
            condition = arrayOf(selectedType)
            nbChecked++
        }
        if (cbMelodyChecked == true) {
            if (nbChecked == 0) {
                MY_QUERY += " WHERE melody=?"
                condition = arrayOf(selectedMelody)
            } else {
                MY_QUERY += " AND melody=?"
                condition = arrayOf(selectedType, selectedMelody)
            }
            nbChecked++
        }
        if (cbStyleChecked == true) {
            if (nbChecked == 0) {
                MY_QUERY += " WHERE Style=?"
                condition = arrayOf(selectedStyle)
            } else if (nbChecked == 1) {
                MY_QUERY += " AND style=?"
                condition = if (cbMelodyChecked == true) {
                    arrayOf(selectedMelody, selectedStyle)
                } else arrayOf(selectedType, selectedStyle)
            } else {
                MY_QUERY += " AND style=?"
                condition = arrayOf(selectedType, selectedMelody, selectedStyle)
            }
            nbChecked++
        }
        if (nbChecked > 0) c = newDB.rawQuery(MY_QUERY, condition)
        //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    results.add(
                        c.getString(c.getColumnIndexOrThrow("Type"))
                                + " " + c.getString(c.getColumnIndexOrThrow("Melody"))
                                + " " + c.getString(c.getColumnIndexOrThrow("Style"))
                    )
                } while (c.moveToNext())
            }
        }
        c.close()
        textView?.text = results.toString()
    }
}