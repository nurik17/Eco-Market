package com.example.ecomarket.presentation.basket

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomarket.databinding.FragmentBasketBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    private val viewModel: BasketViewModel by viewModels()
    private val basketAdapter = BasketAdapter()

    override fun onBindView() {
        super.onBindView()
        setupRecyclerView()
        getBasketProducts()
    }
    private fun getBasketProducts(){
        viewModel.getAllBasketProducts().observe(viewLifecycleOwner){items->
            basketAdapter.submitList(items)
        }
    }
    private fun setupRecyclerView() = with(binding.basketRv) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = basketAdapter
        addItemDecoration(OffsetDecoration(bottom = 10))
    }
}