package com.mtjin.bungsegwon.views.enroll_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mtjin.bungsegwon.base.BaseViewModel
import com.mtjin.bungsegwon.model.Menu

class EnrollDetailViewModel : BaseViewModel() {
    private val _menuList = MutableLiveData<MutableList<Menu>>()

    val menuList: LiveData<MutableList<Menu>> get() = _menuList
}