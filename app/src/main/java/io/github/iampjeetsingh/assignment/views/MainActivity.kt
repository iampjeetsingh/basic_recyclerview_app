package io.github.iampjeetsingh.assignment.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.iampjeetsingh.assignment.R
import io.github.iampjeetsingh.assignment.adapters.UsersAdapter
import io.github.iampjeetsingh.assignment.databinding.ActivityMainBinding
import io.github.iampjeetsingh.assignment.db.UserDatabase
import io.github.iampjeetsingh.assignment.repository.UserRepository
import io.github.iampjeetsingh.assignment.viewmodels.MainViewModel
import io.github.iampjeetsingh.assignment.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = UserDatabase.getDatabase(application).userDao()
        val repository = UserRepository(dao)
        val adapter = UsersAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository, applicationContext)).get(MainViewModel::class.java)
        mainViewModel.getUsers().observe(this, {
            adapter.users.clear()
            adapter.users.addAll(it)
            adapter.notifyDataSetChanged()
        })
        mainViewModel.showBanner.observe(this, {
            if(it)
                binding.bannerLayout.visibility = INVISIBLE
            else
                binding.bannerLayout.visibility = VISIBLE
        })

        binding.bannerCloseButton.setOnClickListener {
            mainViewModel.closeBanner()
        }
    }
}