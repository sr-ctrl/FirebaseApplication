package com.example.firebaseapplication.network.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val name:String?,
    val password:String,
):Parcelable

@Parcelize
class RegisterUser(
    val name:String?,
    val email:String,
    val password:String,
):Parcelable