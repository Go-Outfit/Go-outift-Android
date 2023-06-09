package com.example.gooutfit_android.ui.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.gooutfit_android.R
import com.example.gooutfit_android.ui.auth.login.LoginActivity
import com.example.gooutfit_android.ui.auth.viewmodel.AuthResponse
import com.example.gooutfit_android.ui.auth.viewmodel.AuthViewModel
import com.example.gooutfit_android.ui.dashboard.DashboardActivity
import com.google.android.play.integrity.internal.t
import com.google.firebase.auth.FirebaseAuth

class SplashFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var authResponse = AuthResponse()
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()

//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


        Handler().postDelayed({
            val context = requireContext().applicationContext // Mendapatkan konteks aplikasi
            val token = getTokenFromSharedPreferences(context) // Mengambil token dari Shared Preferences

            if(token != null){
                Log.e("Splash", "Token ada : $token")
                startActivity()
            }else{
                Log.e("Splash", "Token Kosong")
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun startActivity(){
        val intent = Intent(activity, DashboardActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("login_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }
}