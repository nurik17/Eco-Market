package com.example.ecomarket.presentation.basket

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.FragmentBasketBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.example.ecomarket.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    private val viewModel: BasketViewModel by viewModels()
    private val basketAdapter = BasketAdapter{ clickableView, item ->
        onBasketClick(clickableView, item)
    }

    private var overallPrice: Double = 0.0
    override fun onBindView() {
        super.onBindView()
        setupRecyclerView()
        getBasketProducts()
        navigateToProductFragment()
        clearBasket()
    }

    private fun clearBasket(){
        binding.textClear.setSafeOnClickListener {
            viewModel.deleteAllProduct()
        }
    }
    private fun onBasketClick(clickableView: BasketViewHolder.ClickableView, item: ProductListItem) {
        when (clickableView) {
            BasketViewHolder.ClickableView.ONMINUSCLICK -> {
                viewModel.decrementProductQuantity(item)
            }
            BasketViewHolder.ClickableView.ONPLUSCLICK -> {
                viewModel.incrementProductQuantity(item)
            }
            BasketViewHolder.ClickableView.ONDELETECLICK->{
                viewModel.deleteProductById(item)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrices(items: List<ProductListItem>) {
        val totalPrice = calculateTotalPrice(items)
        binding.textSum.text = "$totalPrice тг"
        val deliverPrice = 150.0
        binding.textSumDeliver.text = "$deliverPrice тг"
        overallPrice = totalPrice + deliverPrice
        binding.textSumTotal.text = "$overallPrice тг"
    }

    @SuppressLint("SetTextI18n")
    private fun getBasketProducts(){
        viewModel.getAllBasketItems().observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                showEmptyBasketView()
            } else {
                hideEmptyBasketView()
                basketAdapter.submitList(items)
                updatePrices(items)
            }
        }
    }

    private fun calculateTotalPrice(items: List<ProductListItem>): Double {
        var totalPrice = 0.0
        for (item in items) {
            totalPrice += item.price.toDouble() * item.quantity
        }
        return totalPrice
    }


    private fun navigateToMakeOrder(){
        binding.makeOrderBtn.setSafeOnClickListener {
            if(overallPrice < 300.0){
                findNavController().navigate(R.id.)
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
        binding.apply {
            imageEmpty.visibility = View.VISIBLE
            tvEmpty.visibility = View.VISIBLE
            emptyNavigateStoreBtn.visibility = View.VISIBLE

        }
    }
    private fun hideEmptyBasketView() {
        binding.imageEmpty.visibility = View.GONE
        binding.tvEmpty.visibility = View.GONE
        binding.emptyNavigateStoreBtn.visibility = View.GONE
    }
}