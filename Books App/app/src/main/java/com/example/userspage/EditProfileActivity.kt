package com.example.userspage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class EditProfileActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var saveButton: Button

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editPhone = findViewById(R.id.editPhone)
        saveButton = findViewById(R.id.saveButton)

        @Suppress("DEPRECATION")
        user = intent.getParcelableExtra("user")!!


        editName.setText(user.name)
        editEmail.setText(user.email)
        editPhone.setText(user.phone)

        saveButton.setOnClickListener {
            user.name = editName.text.toString()
            user.email = editEmail.text.toString()
            user.phone = editPhone.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("user", user)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}

