package com.example.firebaseapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewCreated()
    }

    protected fun onViewCreated(){
        observer()
        load()
    }

    protected abstract fun observer()
    protected abstract fun load()
}