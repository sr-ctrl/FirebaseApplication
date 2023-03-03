package com.example.firebaseapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.core.view.setPadding
import com.google.android.material.textfield.TextInputLayout

object Utils {
    fun textInputError(showError:Boolean, inputField: TextInputLayout, error:String? = null) {
        if (showError){
            inputField.isErrorEnabled = true
            inputField.error = error
            inputField.removePadding()
        }else{
            inputField.isErrorEnabled = false
            inputField.error = null
        }
    }
    fun TextInputLayout.removePadding() {
        for (i in 0 until this.childCount) {
            this.getChildAt(i).setPadding(0)
        }
    }
    fun validate(name:String,password:String): UserValidate {
        if (name.isEmpty() || name.isBlank()) {
            return UserValidate.INVALIDUSER
        }
        if (password.isEmpty() || password.isBlank()) {
            return UserValidate.INVALIDPASSWORD
        }
        return UserValidate.VALID
    }
    fun validate(name:String,email:String,password:String): ValidateSignupUser {
        if (name.isEmpty() || name.isBlank()) {
            return ValidateSignupUser.INVALIDUSER
        }
        if (email.isEmpty() || email.isBlank()) {
            return ValidateSignupUser.INVALIDEMAIL
        }
        if (password.isEmpty() || password.isBlank()) {
            return ValidateSignupUser.INVALIDPASSWORD
        }
        return ValidateSignupUser.VALID
    }
}
enum class UserValidate{
    INVALIDUSER,
    INVALIDPASSWORD,
    VALID
}
enum class ValidateSignupUser{
    INVALIDUSER,
    INVALIDEMAIL,
    INVALIDPASSWORD,
    VALID
}