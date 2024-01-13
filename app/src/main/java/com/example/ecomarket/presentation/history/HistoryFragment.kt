package com.example.ecomarket.presentation.history

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.databinding.FragmentHistoryBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: HistoryViewModel by viewModels()
    private val historyAdapter = HistoryAdapter()

    override fun onBindView() {
        super.onBindView()

        setupRecyclerView()
        getOrderHistoryList()
    }

    private fun setupRecyclerView() = with(binding.historyRv) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = historyAdapter
        addItemDecoration(OffsetDecoration(bottom = 8))
    }

    private fun getOrderHistoryList() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.orderHistory.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val orderHistoryList = state.data ?: emptyList()
                            historyAdapter.submitList(orderHistoryList)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> Unit
                    }
                }
            }
        }
    }
}