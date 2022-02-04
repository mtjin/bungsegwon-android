package com.mtjin.bungsegwon.views.main.enroll

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseFragment
import com.mtjin.bungsegwon.databinding.FragmentMainEnrollBinding
import com.mtjin.bungsegwon.views.main.MainFragmentDirections


class MainEnrollFragment : BaseFragment<FragmentMainEnrollBinding>(R.layout.fragment_main_enroll) {
    override fun init() {
        initEvent()
    }

    private fun initEvent() {
        // 매장 등록화면 이동
        binding.btnEnroll.setOnClickListener {
            Log.d("AAAAAA", "AASDSADASD")
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToEnrollDetailFragment())
        }
    }

}