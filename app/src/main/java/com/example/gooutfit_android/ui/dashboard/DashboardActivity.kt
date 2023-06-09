package com.example.gooutfit_android.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gooutfit_android.R
import com.example.gooutfit_android.databinding.ActivityDashboardBinding
import com.example.gooutfit_android.ui.dashboard.history.HistoryFragment
import com.example.gooutfit_android.ui.dashboard.home.HomeFragment
import com.example.gooutfit_android.ui.dashboard.setting.SettingFragment
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> adapterFragment(HomeFragment())
                R.id.history -> adapterFragment(HistoryFragment())
                R.id.settings -> adapterFragment(SettingFragment())
            }
            true
        }

    }

    private fun adapterFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

}