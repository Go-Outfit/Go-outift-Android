package com.example.gooutfit_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gooutfit_android.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}