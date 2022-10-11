package com.ermain.bitfit_application

import android.app.Application

class BitFitApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}