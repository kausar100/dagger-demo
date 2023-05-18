package com.example.android.dagger.splash

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

@ActivityScope
class SplashViewModel @Inject constructor(val userManager: UserManager) {
    val splashState: SplashViewState
        get() =
            if (!userManager.isUserLoggedIn()) {
                if (!userManager.isUserRegistered()) {
                    SplashToRegistration
                } else {
                    SplashToLogin
                }
            } else {
                SplashToMain
            }


}