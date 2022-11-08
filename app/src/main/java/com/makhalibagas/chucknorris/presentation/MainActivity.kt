package com.makhalibagas.chucknorris.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makhalibagas.chucknorris.databinding.ActivityMainBinding
import com.makhalibagas.chucknorris.domain.domain.Chucknorris
import com.makhalibagas.chucknorris.presentation.adapter.MainAdapter
import com.makhalibagas.chucknorris.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        mainAdapter = MainAdapter()
        binding.rvChucknorris.adapter = mainAdapter
    }

    private fun initListener() {
        binding.apply {
            btSearch.setOnClickListener {
                if (etChucknorris.getString().length >= 3) getChucknorris(etChucknorris.getString())
                else toast("Minimal 3 huruf pencarian")
            }
        }
    }

    private fun initObserver() {
        collectLifecycleFlow(viewModel.chucknorris) { state ->
            when (state) {
                is UiStateWrapper.Loading -> populateLoading(state.isLoading)
                is UiStateWrapper.Success -> populateSucces(state.data)
                is UiStateWrapper.Error -> toast(state.msg)
            }
        }
    }

    private fun getChucknorris(query: String) {
        viewModel.setQuery(query)
        viewModel.getChucknorris()
    }

    private fun populateLoading(isLoading: Boolean) {
        binding.apply {
            pb.isVisible(isLoading)
            rvChucknorris.isVisible(!isLoading)
        }
    }

    private fun populateSucces(chucknorris: List<Chucknorris>) {
        if (chucknorris.isEmpty()) toast("Data Kosong")
        mainAdapter.setData(chucknorris)
    }
}