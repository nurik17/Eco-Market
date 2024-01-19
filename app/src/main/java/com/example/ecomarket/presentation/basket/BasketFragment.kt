package com.example.ecomarket.presentation.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomarket.R
import com.example.ecomarket.domain.ProductListItem
import com.example.ecomarket.databinding.FragmentBasketBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.example.ecomarket.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>(FragmentBasketBinding::inflate) {

    private val viewModel: BasketViewModel by viewModels()
    private val basketAdapter = BasketAdapter { clickableView, item ->
        onBasketClick(clickableView, item)
    }

    private var overallPrice: Double = 0.0
    private var basketItems: List<ProductListItem> = emptyList()

    override fun onBindView() {
        super.onBindView()
        setupRecyclerView()
        getBasketProducts()
        navigateToProductFragment()
        clearBasket()
        navigateToMakeOrder()
    }


    private fun navigateToMakeOrder() {
        binding.makeOrderBtn.setSafeOnClickListener {
            val bundle = Bundle()
            bundle.putFloat("overallPrice", overallPrice.toFloat())

            if (overallPrice < 300.0) {
                findNavController().navigate(R.id.action_basketFragment_to_priceWarningFragment)
            } else {
                bundle.putParcelableArrayList("basketItems", ArrayList(basketItems))
                findNavController().navigate(
                    R.id.action_basketFragment_to_makeOrderFragment,
                    bundle
                )
            }
        }
    }

    private fun uiBugFix() {
        binding.apply {
                basketRv.visibility = View.GONE
                textSum.visibility = View.GONE
                textSumDeliver.visibility = View.GONE
                textSumTotal.visibility = View.GONE
            }
        }

    private fun clearBasket() {
        binding.apply {
            textClear.setSafeOnClickListener {
                viewModel.deleteAllProduct()
                uiBugFix()
            }
        }
    }

    private fun onBasketClick(
        clickableView: BasketViewHolder.ClickableView,
        item: ProductListItem
    ) {
        when (clickableView) {
            BasketViewHolder.ClickableView.ONMINUSCLICK -> {
                viewModel.decrementProductQuantity(item)
            }

            BasketViewHolder.ClickableView.ONPLUSCLICK -> {
                viewModel.incrementProductQuantity(item)
            }

            BasketViewHolder.ClickableView.ONDELETECLICK -> {
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
    private fun getBasketProducts() {
        viewModel.getAllBasketItems().observe(viewLifecycleOwner) { items ->
            if (items.isEmpty()) {
                showEmptyBasketView()
            } else {
                hideEmptyBasketView()
                basketItems = items
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


    private fun navigateToProductFragment() {
        binding.emptyNavigateStoreBtn.setSafeOnClickListener {
            findNavController().navigate(R.id.action_basketFragment_to_productFragment)
        }
    }

    private fun setupRecyclerView() = with(binding.basketRv) {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = basketAdapter
        addItemDecoration(OffsetDecoration(bottom = 12))
    }

    private fun showEmptyBasketView() {
        binding.apply {
            imageEmpty.visibility = View.VISIBLE
            tvEmpty.visibility = View.VISIBLE
            emptyNavigateStoreBtn.visibility = View.VISIBLE
            textTitleSum.visibility = View.GONE
            textTitleDeliver.visibility = View.GONE
            textTitleTotal.visibility = View.GONE
            makeOrderBtn.visibility = View.GONE
        }
    }

    private fun hideEmptyBasketView() {
        binding.apply {
            imageEmpty.visibility = View.GONE
            tvEmpty.visibility = View.GONE
            emptyNavigateStoreBtn.visibility = View.GONE
            textTitleSum.visibility = View.VISIBLE
            textTitleDeliver.visibility = View.VISIBLE
            textTitleTotal.visibility = View.VISIBLE
            makeOrderBtn.visibility = View.VISIBLE
        }
    }
}