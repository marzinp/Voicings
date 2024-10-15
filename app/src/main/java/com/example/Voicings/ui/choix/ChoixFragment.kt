package com.example.Voicings.ui.choix

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Voicings.DBHelper
import com.example.Voicings.R
import com.example.Voicings.TableRowAdapter
import com.example.Voicings.Voicing
import com.example.Voicings.databinding.FragmentChoixBinding


class ChoixFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var binding: FragmentChoixBinding? = null
    private var selectedType: String? = null
    private var selectedMelody: String? = null
    private var selectedStyle: String? = null
    private var typeSpinner: Spinner? = null
    private var melodySpinner: Spinner? = null
    private var styleSpinner: Spinner? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val choixViewModel =
            ViewModelProvider(this)[ChoixViewModel::class.java]

        binding = FragmentChoixBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        //val textView = binding?.displayChordsList


        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var results = ArrayList<String?>()
        val dbHelper = DBHelper(this.context)
        val newDB = dbHelper.writableDatabase
        typeSpinner = binding?.TypeSpinner
        melodySpinner = binding?.MelodySpinner
        styleSpinner = binding?.StyleSpinner
        results.add("All")
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
        c?.close()
        var text = view.findViewById<TextView>(R.id.typeSpinnerLabel)
        text.text = "Chord Types"
        // Specify the layout to use when the list of choices appears.
        var adapter: ArrayAdapter<*> =
            ArrayAdapter(this.requireContext(), R.layout.spinner_center_item, results)
        var spinner = typeSpinner
        // Apply the adapter to the spinner.
        spinner?.adapter = adapter
        spinner?.onItemSelectedListener = this
        selectedType = spinner?.selectedItem.toString()
        //Melody Spinner
        results = ArrayList()
        results.add("All")
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
        c?.close()
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
        results.add("All")
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
        c?.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onItemSelected(
        parent: AdapterView<*>?, view: View,
        pos: Int, id: Long
    ) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        when (parent?.id) {
            R.id.TypeSpinner -> selectedType = parent.getItemAtPosition(pos).toString()
            R.id.MelodySpinner -> selectedMelody = parent.getItemAtPosition(pos).toString()
            R.id.StyleSpinner -> selectedStyle = parent.getItemAtPosition(pos).toString()
        }
        var spinner = typeSpinner

        //}
        // else if(view==this.getView().findViewById(R.id.MelodySpinner)) {
        spinner = melodySpinner
        selectedMelody = spinner?.selectedItem.toString()
        //}
        //else if(view==this.getView().findViewById(R.id.StyleSpinner)) {
        spinner = styleSpinner
        selectedStyle = spinner?.selectedItem.toString()
        this.displayResults()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    private fun displayResults() {
        val results = ArrayList<Voicing>()
        val dbHelper = DBHelper(context)
        val newDB = dbHelper.readableDatabase
        var nbChecked = 0
        if (selectedType == "All") selectedType = "%"
        if (selectedMelody == "All") selectedMelody = "%"
        if (selectedStyle == "All") selectedStyle = "%"
        var crits = arrayOf(selectedType, selectedMelody, selectedStyle)
        var MY_QUERY = "SELECT * FROM Voicings WHERE type LIKE? AND melody LIKE? AND style LIKE?"
        var c: Cursor? = newDB.rawQuery(MY_QUERY, crits)

        //Now iterate with cursor
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    var voicing = Voicing()
                    voicing.voicingId = c.position
                    voicing.type = c.getString(c.getColumnIndexOrThrow("Type"))
                    voicing.melody = c.getString(c.getColumnIndexOrThrow("Melody"))
                    voicing.style = c.getString(c.getColumnIndexOrThrow("Style"))
                    voicing.LH = c.getString(c.getColumnIndexOrThrow("LH"))
                    voicing.RH = c.getString(c.getColumnIndexOrThrow("RH"))
                    results.add(voicing)
                } while (c.moveToNext())
            }
        }
        c?.close()
        val tableRecyclerView: RecyclerView? = binding?.tableRecyclerView
        var tableRowAdapter: TableRowAdapter = TableRowAdapter(results)
        tableRecyclerView?.layoutManager = LinearLayoutManager(this.context)
        tableRecyclerView?.adapter = tableRowAdapter
    }
}