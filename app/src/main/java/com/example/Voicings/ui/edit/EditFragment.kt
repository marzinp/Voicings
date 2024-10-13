package com.example.Voicings.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.Voicings.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var binding: FragmentEditBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val editViewModel =
            ViewModelProvider(this).get(EditViewModel::class.java)

        binding = FragmentEditBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        val textView = binding!!.textEdit
        editViewModel.text.observe(viewLifecycleOwner) { text: CharSequence? ->
            textView.text = text
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}