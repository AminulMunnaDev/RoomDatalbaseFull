package com.example.introductiontoroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.introductiontoroom.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AddEditPersonFragment.AddEditPersonListener {
    private lateinit var binding: ActivityMainBinding
    private var dao: PersonDao? = null
    private lateinit var adapter : PersonDetailsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        attachUiListener()
        subscribeDataStreams()
    }
    private fun subscribeDataStreams()  {

        lifecycleScope.launch {
            dao?.getAllData()?.collect{mList->
                adapter.submitList(mList)
            }
        }

    }

    private fun initVars() {
        dao = AppDatabase.getDatabase(this).personDao()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter = PersonDetailsAdapter()
        binding.recyclerView.adapter = adapter

    }

    private fun attachUiListener() {
        binding.floatingActionButton.setOnClickListener {
            showBottomSheet()
        }

    }

    private fun showBottomSheet() {

        val bottomSheet = AddEditPersonFragment(this)
        bottomSheet.show(supportFragmentManager, AddEditPersonFragment.TAG)
    }

    override fun onSaveBtnClicked(personModel: PersonModel) {
        lifecycleScope.launch(Dispatchers.IO) {
            dao?.savePerson(personModel)
        }

    }

}