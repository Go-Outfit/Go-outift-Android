package com.example.gooutfit_android.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gooutfit_android.R
import com.example.gooutfit_android.databinding.ActivityLoginBinding
import com.example.gooutfit_android.ui.auth.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvHaventAccount.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}