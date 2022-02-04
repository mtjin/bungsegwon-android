package com.mtjin.bungsegwon.views.setting

import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseFragment
import com.mtjin.bungsegwon.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun init() {
        initView()
    }

    private fun initView() {
        binding.toolbar.tvTitle.text = "설정"
    }
}