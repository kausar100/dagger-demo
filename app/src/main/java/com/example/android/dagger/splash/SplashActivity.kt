package com.example.android.dagger.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import javax.inject.Inject

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // 2) Grab userManager from appComponent to check if the user is logged in or not
        val userManager = (application as MyApplication).appComponent.userManager()
        splashViewModel.selectNextScreen(userManager)


        Handler(Looper.getMainLooper()).postDelayed({
            when (splashViewModel.splashState) {
                SplashToRegistration -> {
                    startActivity(Intent(this, RegistrationActivity::class.java))
                    finish()
                }
                SplashToLogin -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                SplashToMain -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }

        }, 3000)
    }
}

sealed class SplashViewState
object SplashToRegistration : SplashViewState()
object SplashToLogin : SplashViewState()
object SplashToMain : SplashViewState()