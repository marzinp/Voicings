package com.example.Voicings.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {
    private val mText = MutableLiveData<String?>()

    init {
        mText.value = "This is slideshow fragment"
    }

    val text: LiveData<String?>
        get() = mText
}