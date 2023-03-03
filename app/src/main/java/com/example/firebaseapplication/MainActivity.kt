package com.example.firebaseapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.firebaseapplication.base.BaseActivity
import com.example.firebaseapplication.databinding.ActivityMainBinding
import com.example.firebaseapplication.network.dto.RegisterUser
import com.example.firebaseapplication.network.dto.User

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var user:RegisterUser? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        load()
        initUI()
        setListner()
    }

    private fun initUI(){
        binding.username.setText(user?.name)
        binding.username.text = "Username: ${user?.name}"
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun load() {
        val intent = getIntent().extras
         user = intent?.getParcelable("user")
        Log.d("TAG", "load: user ${user?.name} pass${user?.password} ")
        binding.username.setText(user?.name)
    }

    private fun setListner(){

    }


}