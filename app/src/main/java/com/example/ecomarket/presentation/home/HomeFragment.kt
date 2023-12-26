package com.example.ecomarket.presentation.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.databinding.FragmentHomeBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun onBindView() {
        super.onBindView()

        setupRecyclerView()
        fetchCategoryList()
    }

    private fun setupRecyclerView() {
        binding.categoryRv.apply {
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2, GridLayoutManager.VERTICAL, false
                )
            adapter = categoryAdapter
            addItemDecoration(OffsetDecoration(start = 10, bottom = 10))
        }
    }

    private fun fetchCategoryList() {
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryList.collect { item ->
                    when (item) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            bottomNavigationView.visibility = View.INVISIBLE
                        }

                        is Resource.Success -> {
                            val categoryList = item.data ?: emptyList()
                            categoryAdapter.setData(categoryList)
                            binding.progressBar.visibility = View.INVISIBLE
                            bottomNavigationView.visibility = View.VISIBLE
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            bottomNavigationView.visibility = View.INVISIBLE
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}

/*
lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.productsPrice.collectLatest { price->*/
