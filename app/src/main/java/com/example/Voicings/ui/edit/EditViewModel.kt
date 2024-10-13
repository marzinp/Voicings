package com.example.Voicings.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditViewModel : ViewModel() {
    private val mText = MutableLiveData<String?>()

    init {
        mText.value = "This is edit fragment"
    }

    val text: LiveData<String?>
        get() = mText
}