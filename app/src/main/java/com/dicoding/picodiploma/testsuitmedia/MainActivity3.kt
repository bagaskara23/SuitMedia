package com.dicoding.picodiploma.testsuitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.testsuitmedia.adapter.AdapterUser
import com.dicoding.picodiploma.testsuitmedia.adapter.LoadingStateAdapter
import com.dicoding.picodiploma.testsuitmedia.databinding.ActivityMain3Binding
import com.dicoding.picodiploma.testsuitmedia.dataclass.ListUserItem

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var adapter: AdapterUser
    private lateinit var preferenceHelper: PreferenceHelper

    private val mainViewModel: MainViewModel3 by viewModels {
        MainViewModel3.ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()!!.title = "Third Screen"

        adapter = AdapterUser()

        adapter.setOnItemClickCallback(object : AdapterUser.OnItemClickCallback {
            override fun onItemClicked(data: ListUserItem) {
                preferenceHelper.put(PreferenceHelper.CHOOSE_NAME, data.first_name +" "+data.last_name)
            }
        })

            mainViewModel.user.observe(this, {
                adapter.submitData(lifecycle, it)
            })

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity3)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
        }
    }
}