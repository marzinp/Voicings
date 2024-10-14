package com.example.Voicings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TableRowAdapter(private var userArrayList: ArrayList<Voicing>) :
    RecyclerView.Adapter<TableRowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.table_row_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.voicing_Type_text.text = userArrayList[i].type
        viewHolder.voicing_Melody_text.text = userArrayList[i].melody
        viewHolder.voicing_Style_text.text = userArrayList[i].style
        viewHolder.voicing_LH_text.text = userArrayList[i].LH
        viewHolder.voicing_RH_text.text = userArrayList[i].RH
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var voicing_Type_text: TextView = itemView.findViewById(R.id.voicing_Type_text)
        val voicing_Melody_text: TextView = itemView.findViewById(R.id.voicing_Melody_text)
        val voicing_Style_text: TextView = itemView.findViewById(R.id.voicing_Style_text)
        val voicing_LH_text: TextView = itemView.findViewById(R.id.voicing_LH_text)
        val voicing_RH_text: TextView = itemView.findViewById(R.id.voicing_RH_text)
    }
}