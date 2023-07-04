package dev.android.appbusesdriver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import dev.android.appbusesdriver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }
}