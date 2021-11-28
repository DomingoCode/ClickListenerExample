package com.example.clicklisteneractivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clicklisteneractivity.adapter.StateListRVAdapter
import com.example.clicklisteneractivity.databinding.ActivityMainBinding
import com.example.clicklisteneractivity.model.State
import com.example.clicklisteneractivity.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var stateListAdapter = StateListRVAdapter()
    private lateinit var viewModel: MainViewModel
    
    
    
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getStateList()
        
        initRV()
        observeViewModel()
        
        setContentView(binding.root)
    }
    
    private fun initRV() = with(binding) {
        Log.e("andrey", "initRV ")
        stateListRV.setHasFixedSize(true)
        stateListRV.layoutManager = LinearLayoutManager(this@MainActivity)
        val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
        stateListRV.addItemDecoration(decoration)
        stateListAdapter = StateListRVAdapter()
        stateListAdapter.apply {
            onClicked = ::adapterOnClick//refer correspondent methods
        }
        stateListRV.adapter = stateListAdapter
    }
    
    private fun observeViewModel() {
        viewModel.stateListResponseLiveData.observe(this, Observer {
            it?.let {
                stateListAdapter.setUpdatedList(it.data)
            }
        })
    }
    
    private fun adapterOnClick(item: State) {
        Toast.makeText(this, "onItemClick item = ${item.state}", Toast.LENGTH_LONG).show()
    }
    
    
}