package com.example.Voicings.ui.choix

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChoixViewModel : ViewModel() {
    private val mText = MutableLiveData<String>()

    init {
        mText.value = "This is tables fragment"
    }

    val text: LiveData<String>
        get() = mText
}