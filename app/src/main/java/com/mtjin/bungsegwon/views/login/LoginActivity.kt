package com.mtjin.bungsegwon.views.login

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mtjin.bungsegwon.R
import com.mtjin.bungsegwon.base.BaseActivity
import com.mtjin.bungsegwon.databinding.ActivityLoginBinding
import com.mtjin.bungsegwon.utils.data.RC_GOOGLE_LOGIN
import com.mtjin.bungsegwon.utils.data.User
import com.mtjin.bungsegwon.views.main.MainActivity
import com.mtjin.bungsegwon.views.signup.SignUpActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initGoogleLogin()
        initEvent()
    }

    private fun initView() {
        binding.toolbar.tvTitle.text = "로그인"
    }

    override fun onStart() {
        super.onStart()
        // 로그인 하세요 커스텀 토스트 메시지 show
        val inflater = LayoutInflater.from(this)
        val toastBinding = inflater.inflate(
            R.layout.custom_toast_login,
            findViewById(R.id.layout_custom_toast_root)
        )
        Toast(this).apply {
            setGravity(
                Gravity.TOP or Gravity.CENTER,
                0,
                (200 * Resources.getSystem().displayMetrics.density).toInt()
            )
            duration = Toast.LENGTH_LONG
            view = toastBinding
        }.show()
    }

    private fun initEvent() {
        binding.btnGoogleLogin.setOnClickListener {
            googleLogin()
        }
    }

    private fun initGoogleLogin() {
        auth = FirebaseAuth.getInstance()
        auth.currentUser?.let { // 이미 구글로그인 되있는 경우
            User.uuid = it.uid
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GOOGLE_LOGIN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                account.let { it?.let { firebaseAuthWithGoogle(it) } }
            } catch (e: ApiException) {
                Log.d(TAG, "onActivityResult() -> " + e.message.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    User.uuid = auth.currentUser?.uid.toString()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.d(TAG, "firebaseAuthWithGoogle() -> " + task.exception?.message.toString())
                }
            }
    }

    private fun googleLogin() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_LOGIN)
    }

    companion object {
        const val TAG = "LoginActivityT"
    }
}