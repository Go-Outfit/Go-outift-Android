package com.example.gooutfit_android.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gooutfit_android.R
import com.example.gooutfit_android.databinding.FragmentFirstQuestionBinding
import com.example.gooutfit_android.databinding.FragmentHomeBinding
import com.example.gooutfit_android.ui.recommendation.FindOutfitActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnFindOutfit.setOnClickListener {
            val intent = Intent(activity, FindOutfitActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}