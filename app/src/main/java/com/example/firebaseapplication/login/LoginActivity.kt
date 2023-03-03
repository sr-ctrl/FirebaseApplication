package com.example.firebaseapplication.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.example.firebaseapplication.MainActivity
import com.example.firebaseapplication.R
import com.example.firebaseapplication.base.BaseActivity
import com.example.firebaseapplication.databinding.ActivityLoginBinding
import com.example.firebaseapplication.network.dto.RegisterUser
import com.example.firebaseapplication.network.dto.User
import com.example.firebaseapplication.utils.UserValidate
import com.example.firebaseapplication.utils.Utils
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    private var registeredUser:RegisterUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        load()
        clickListner()
    }
    private fun load(){
        val intent = getIntent().extras
        registeredUser = intent?.getParcelable<RegisterUser>("registerUser")
        Log.d("TAG", "load: user ${registeredUser?.name} pass${registeredUser?.password}email${registeredUser?.password} ")
       if (registeredUser != null){
           registeredUser.let {
               binding.edtxUsername.setText(it?.name)
               binding.edtxpassword.setText(it?.password)
           }
       }
    }

    private fun clickListner() {
        binding.btLogin.setOnClickListener {
            val name = binding.edtxUsername.text.toString()
            val password = binding.edtxpassword.text.toString()
            val user = User(name = name, password = password)
            when(Utils.validate(name,password)){
                UserValidate.INVALIDUSER -> {
                    Toast.makeText(this,"please enter user name",Toast.LENGTH_SHORT).show()
                    binding.layoutusername.isErrorEnabled = true
                    Utils.textInputError(true,binding.layoutusername,"please enter user name")
                }
                UserValidate.INVALIDPASSWORD -> {
                    Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show()
                    Utils.textInputError(true,binding.layoutpassword,"please enter user name")
                }
                UserValidate.VALID -> {
                    Utils.textInputError(false,binding.layoutpassword,null)
                    Utils.textInputError(false,binding.layoutusername,null)
                    val intent = Intent(this,MainActivity::class.java)
                    intent.putExtra("user",user)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }
            }

            }

        binding.txtsignup.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}






