package com.americanairlines.shlaces_shareplaces

import android.app.Application
import com.google.firebase.FirebaseApp

class ShlacesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}