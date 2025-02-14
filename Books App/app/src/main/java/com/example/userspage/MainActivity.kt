package com.example.userspage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var userImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var userPhone: TextView
    private lateinit var editProfileButton: Button

    private var user = User("Иван Иванов", "ivan@example.com", "+79990001122", null)

    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedUser = result.data?.getParcelableExtra<User>("user")
            if (updatedUser != null) {
                user = updatedUser
                updateUI()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userImage = findViewById(R.id.userImage)
        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        userPhone = findViewById(R.id.userPhone)
        editProfileButton = findViewById(R.id.editProfileButton)

        updateUI()

        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("user", user)
            editProfileLauncher.launch(intent)
        }
    }

    private fun updateUI() {
        userName.text = user.name
        userEmail.text = user.email
        userPhone.text = user.phone
        user.imageUri?.let {
            userImage.setImageURI(Uri.parse(it))
        }
    }
}