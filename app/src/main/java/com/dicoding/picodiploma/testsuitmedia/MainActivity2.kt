package com.dicoding.picodiploma.testsuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.testsuitmedia.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var preferenceHelper: PreferenceHelper
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.title = "Second Screen"

        binding.apply{
            username.text = preferenceHelper.getString(PreferenceHelper.NAME).toString()
        }

        binding.apply{
            selectedUsername.text = preferenceHelper.getString(PreferenceHelper.CHOOSE_NAME).toString()
        }

        setupAction()
    }

    private fun setupAction() {
        binding.chooseUser.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }

}