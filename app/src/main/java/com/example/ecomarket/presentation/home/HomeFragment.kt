package com.example.ecomarket.presentation.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
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

    private val categoryAdapter = CategoryAdapter {
        onCategoryItemClick()
    }

    override fun onBindView() {
        super.onBindView()

        setupRecyclerView()
        fetchCategoryList()
    }

    private fun onCategoryItemClick() {
        findNavController().navigate(R.id.action_homeFragment_to_productFragment)
    }

    private fun setupRecyclerView() {
        binding.categoryRv.apply {
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2, GridLayoutManager.VERTICAL, false
                )
            adapter = categoryAdapter
            addItemDecoration(OffsetDecoration(start = 15, bottom = 10))
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

