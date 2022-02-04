package com.mtjin.bungsegwon.views.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseActivity
import com.mtjin.bungsegwon.databinding.ActivitySplashBinding
import com.mtjin.bungsegwon.views.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)
    }
}