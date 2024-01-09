package com.example.ecomarket.presentation.basket

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomarket.R
import com.example.ecomarket.databinding.FragmentBasketBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.example.ecomarket.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    private val viewModel: BasketViewModel by viewModels()
    private val basketAdapter = BasketAdapter()

    override fun onBindView() {
        super.onBindView()
        setupRecyclerView()
        getBasketProducts()
        navigateToProductFragment()
    }
    private fun getBasketProducts(){
        viewModel.getAllBasketItems().observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                showEmptyBasketView()
            } else {
                hideEmptyBasketView()
                basketAdapter.submitList(items)
            }
        }
    }

    private fun navigateToProductFragment() {
        binding.emptyNavigateStoreBtn.setSafeOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_productFragment)
        }
    }

    private fun setupRecyclerView() = with(binding.basketRv) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = basketAdapter
        addItemDecoration(OffsetDecoration(bottom = 10))
    }

    private fun showEmptyBasketView(){
        binding.imageEmpty.visibility = View.VISIBLE
        binding.tvEmpty.visibility = View.VISIBLE
        binding.emptyNavigateStoreBtn.visibility = View.VISIBLE
    }
    private fun hideEmptyBasketView() {
        binding.imageEmpty.visibility = View.GONE
        binding.tvEmpty.visibility = View.GONE
        binding.emptyNavigateStoreBtn.visibility = View.GONE
    }
}