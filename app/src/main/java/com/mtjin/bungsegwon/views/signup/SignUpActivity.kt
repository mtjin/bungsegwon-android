package com.mtjin.bungsegwon.views.signup

import android.content.Intent
import android.os.Bundle
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseActivity
import com.mtjin.bungsegwon.databinding.ActivitySignUpBinding
import com.mtjin.bungsegwon.views.main.MainActivity


class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}