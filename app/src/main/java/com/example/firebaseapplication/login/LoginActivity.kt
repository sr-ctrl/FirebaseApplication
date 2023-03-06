package com.example.firebaseapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.firebaseapplication.MainActivity
import com.example.firebaseapplication.R
import com.example.firebaseapplication.databinding.ActivityLoginBinding
import com.example.firebaseapplication.network.dto.RegisterUser
import com.example.firebaseapplication.utils.UserValidate
import com.example.firebaseapplication.utils.Utils
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    private var registeredUser:RegisterUser? = null
    lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        initui()
        load()
        clickListner()
    }
    private fun initui(){
        mAuth = FirebaseAuth.getInstance()
    }
    private fun load(){
        val intent = getIntent().extras
        registeredUser = intent?.getParcelable<RegisterUser>("registerUser")
        Log.d("TAG", "load: user ${registeredUser?.name} pass${registeredUser?.password}email${registeredUser?.password} ")
       if (registeredUser != null){
           registeredUser.let {
               binding.edtxEmail.setText(it?.name)
               binding.edtxpassword.setText(it?.password)
           }
       }
    }

    private fun clickListner() {
        binding.btLogin.setOnClickListener {
            val email = binding.edtxEmail.text.toString()
            val password = binding.edtxpassword.text.toString()
            val user = RegisterUser(null,email,password)
            when(Utils.validate(email,password)){
                UserValidate.INVALIDUSER -> {
                    Toast.makeText(this,"please enter user name",Toast.LENGTH_SHORT).show()
                    binding.layoutEmail.isErrorEnabled = true
                    Utils.textInputError(true,binding.layoutEmail,"please enter user name")
                }
                UserValidate.INVALIDPASSWORD -> {
                    Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show()
                    Utils.textInputError(true,binding.layoutpassword,"please enter user name")
                }
                UserValidate.VALID -> {
                    Utils.textInputError(false,binding.layoutpassword,null)
                    Utils.textInputError(false,binding.layoutEmail,null)
                    signInUser(user)

                }
            }

            }

        binding.txtsignup.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signInUser(user: RegisterUser){
        mAuth.signInWithEmailAndPassword(user.email,user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val currentUser = mAuth.currentUser?.metadata
                    Log.d("TAG", "signInUser: ${currentUser.toString()}")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user",user)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(baseContext, "Invalid Credential Please check email password.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}






