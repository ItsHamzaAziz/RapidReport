package com.example.rapidreport

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// If dagger needs an object of application class for whole project, it will create an object with this annotation
@HiltAndroidApp
class MyApplication: Application()