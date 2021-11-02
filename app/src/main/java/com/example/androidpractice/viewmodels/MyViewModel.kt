package com.example.androidpractice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidpractice.MyProfiles

class MyViewModel : ViewModel() {

     var myProfiles = MyProfiles()

     private val _title = MutableLiveData<String>()
     val title: LiveData<String>
          get() = _title
     fun updateActionBarTitle(title: String) = _title.postValue(title)



}