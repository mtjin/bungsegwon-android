package com.mtjin.bungsegwon.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.mtjin.bungsegwon.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val _navDirection = MutableLiveData<NavDirections>()
    val navDirection: LiveData<NavDirections> get() = _navDirection
}