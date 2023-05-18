package com.example.android.dagger.splash

import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashViewModel @Inject constructor() {

    lateinit var splashState: SplashViewState

    fun selectNextScreen(userManager: UserManager): SplashViewState {
        splashState = if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                SplashToRegistration
            } else {
                SplashToLogin
            }
        } else {
            SplashToMain
        }

        return splashState
    }

}