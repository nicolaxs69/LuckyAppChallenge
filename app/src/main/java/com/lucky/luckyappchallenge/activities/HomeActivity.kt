package com.lucky.luckyappchallenge.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        supportActionBar?.setCustomView(R.layout.custom_title_bar);
        //initNavigationGraph()
    }

    private fun initNavigationGraph() {
        val navController = findNavController(R.id.navigation_home_fragment)
        //     val navHostFragment = supportFragmentManager.findFragmentById(R.id.navigation_home_fragment) as NavHostFragment
        //     val navController = navHostFragment.navController
    }
}