package com.dicoding.picodiploma.testsuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dicoding.picodiploma.testsuitmedia.PreferenceHelper.Companion.NAME
import com.dicoding.picodiploma.testsuitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        getSupportActionBar()!!.hide()

        setupAction()

    }

    private fun setupAction(){
        binding.checkButton.setOnClickListener {

            val palindrome = binding.palindromeEditText.text.toString().lowercase()

            when {
                palindrome.isEmpty() -> {
                    binding.palindromeEditTextLayout.error = "Masukkan Kata"
                    Toast.makeText(applicationContext, "Kosong", Toast.LENGTH_LONG).show()
                }
                else -> {
                    if(palindrome == palindrome.reversed()){
                        Toast.makeText(applicationContext, "isPalindrome", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(applicationContext, "not palindrome", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.nextButton.setOnClickListener {

            val name = binding.nameEditText.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameEditTextLayout.error = "Masukkan Nama"
                }
                else -> {
                    preferenceHelper.put(NAME, name)
                    startActivity(Intent(this, MainActivity2::class.java))
                }
            }

        }
    }
}