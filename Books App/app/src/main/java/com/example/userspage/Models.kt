package com.example.userspage

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var email: String,
    var phone: String,
    var imageUri: String? = null
) : Parcelable