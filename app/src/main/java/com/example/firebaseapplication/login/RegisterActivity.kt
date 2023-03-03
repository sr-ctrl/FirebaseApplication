package com.example.firebaseapplication.login

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebaseapplication.R
import com.example.firebaseapplication.databinding.ActivityRegisterBinding
import com.example.firebaseapplication.network.dto.RegisterUser
import com.example.firebaseapplication.utils.Utils
import com.example.firebaseapplication.utils.ValidateSignupUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        initui()
        load()
        setListner()
    }
    private fun initui(){
        auth = FirebaseAuth.getInstance()
    }
    private fun load(){}
    private fun setListner(){
        binding.btnSignup.setOnClickListener {
            val name = binding.edtxUsername.text.toString()
            val email = binding.edtxemail.text.toString()
            val password = binding.edtxpassword.text.toString()
            val registerUser = RegisterUser(name, email, password)
            when(Utils.validate(name,email,password)){
                ValidateSignupUser.INVALIDUSER -> {
                    Toast.makeText(this,"please enter user name", Toast.LENGTH_SHORT).show()
                    binding.layoutusername.isErrorEnabled = true
                    Utils.textInputError(true,binding.layoutusername,"please enter user name")
                }
                ValidateSignupUser.INVALIDPASSWORD -> {
                    Toast.makeText(this,"please enter password", Toast.LENGTH_SHORT).show()
                    Utils.textInputError(true,binding.layoutpassword,"please enter user name")
                }
                ValidateSignupUser.INVALIDEMAIL -> {
                    Toast.makeText(this,"please enter password", Toast.LENGTH_SHORT).show()
                    Utils.textInputError(true,binding.layoutemail,"please enter email name")
                }
                ValidateSignupUser.VALID -> {
                    Utils.textInputError(false,binding.layoutpassword,null)
                    Utils.textInputError(false,binding.layoutusername,null)
                    signUpUser(registerUser)

                }

            }

        }
    }
    private fun signUpUser(registerUser: RegisterUser){
        Log.d("TAG", "registerUser: ${registerUser.email},${registerUser.password}")
        auth.createUserWithEmailAndPassword(registerUser.email, registerUser.password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.d("TAG", "createUserWithEmail:success $user")
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("registerUser",registerUser)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}