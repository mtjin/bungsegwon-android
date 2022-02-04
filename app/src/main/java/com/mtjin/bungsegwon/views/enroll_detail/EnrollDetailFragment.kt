package com.mtjin.bungsegwon.views.enroll_detail

import androidx.navigation.fragment.findNavController
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseFragment
import com.mtjin.bungsegwon.databinding.FragmentEnrollDetailBinding

class EnrollDetailFragment :
    BaseFragment<FragmentEnrollDetailBinding>(R.layout.fragment_enroll_detail) {

    private lateinit var enrollMenuAdapter: EnrollMenuAdapter

    override fun init() {
        initView()
        initAdapter()
        initEvent()
    }

    private fun initEvent() {
        binding.ivLocationSearch.setOnClickListener {
            findNavController().navigate(EnrollDetailFragmentDirections.actionEnrollDetailFragmentToLocationEnrollFragment())
        }
    }

    private fun initView() {
        binding.toolbar.tvTitle.text = "등록하기"
    }

    private fun initAdapter() {

        enrollMenuAdapter = EnrollMenuAdapter(
            itemClick = { item ->

            }
        )
        binding.rvMenus.adapter = enrollMenuAdapter
    }
}