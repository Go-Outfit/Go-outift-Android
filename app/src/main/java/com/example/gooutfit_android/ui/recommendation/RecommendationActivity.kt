package com.example.gooutfit_android.ui.recommendation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gooutfit_android.R
import com.example.gooutfit_android.databinding.ActivityRecommendationBinding
import com.example.gooutfit_android.ui.dashboard.DashboardActivity

class RecommendationActivity : AppCompatActivity() {

    private var _binding: ActivityRecommendationBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRecommendationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val observationBundle = intent.getBundleExtra("observationBundle")
        if (observationBundle != null) {
            val topLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val bottomLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            val footLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            val footwearList = observationBundle.getStringArrayList("footwearList")
            val topwearList = observationBundle.getStringArrayList("topwearList")
            val bottomwearList = observationBundle.getStringArrayList("bottomwearList")

            // Create and set up the RecyclerView adapters
            val topAdapter = topwearList?.let { RecycleViewTopAdapter(it) }
            binding.rvTopsRecommendation.adapter = topAdapter
            binding.rvTopsRecommendation.layoutManager = topLayoutManager

            val bottomAdapter = bottomwearList?.let { RecycleViewBottomAdapter(it) }
            binding.rvBottomsRecommendation.adapter = bottomAdapter
            binding.rvBottomsRecommendation.layoutManager = bottomLayoutManager

            val footAdapter = footwearList?.let { RecycleViewFootAdapter(it) }
            binding.rvFootwearRecommendation.adapter = footAdapter
            binding.rvFootwearRecommendation.layoutManager = footLayoutManager

        }

        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this@RecommendationActivity, DashboardActivity::class.java)
            startActivity(intent)
        }

        val plusButton: ImageView = findViewById(R.id.searchButton)
        plusButton.setOnClickListener {
            val intent = Intent(this@RecommendationActivity, FindOutfitActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // Clear the binding reference to avoid potential memory leaks
        _binding = null
    }
}