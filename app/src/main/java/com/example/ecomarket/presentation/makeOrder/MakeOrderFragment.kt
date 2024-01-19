package com.example.ecomarket.presentation.makeOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ecomarket.R
import com.example.ecomarket.domain.OrderRequest
import com.example.ecomarket.domain.OrderedItem
import com.example.ecomarket.domain.OrderedProduct
import com.example.ecomarket.domain.ProductListItem
import com.example.ecomarket.databinding.FragmentMakeOrderBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.onTextChanged
import com.example.ecomarket.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MakeOrderFragment :
    BaseFragment<FragmentMakeOrderBinding>(FragmentMakeOrderBinding::inflate) {

    private val viewModel: MakeOrderViewModel by viewModels()

    override fun onBindView() {
        super.onBindView()

        writeInfo()
        setupTextChangeListeners()
        updateOverallPrice()
        setupBackButton()

        makeOrder()
    }

    private fun makeOrder() {
        binding.orderBtn.setSafeOnClickListener {
            val phoneNumber = binding.phoneNumber.text.toString()
            val address = binding.address.text.toString()
            val reference = binding.landmark.text.toString()
            val comments = binding.comments.text.toString()
            val basketItems = arguments?.getParcelableArrayList<ProductListItem>("basketItems")
            val products = basketItems?.map { OrderedProduct(product = it.id, quantity = it.quantity) }
                ?: emptyList()

            val orderRequest = OrderRequest(
                products = products,
                phone_number = phoneNumber,
                address = address,
                comments = comments,
                reference_point = reference,
            )

            lifecycleScope.launch {
                try {
                    viewModel.makeOrder(orderRequest).let { orderedItem ->
                        navigateToSuccessFragment(orderedItem)
                    }
                } catch (e: Exception) {
                    Log.e("MakeOrderFragment", e.message.toString())
                }
            }
        }
    }

    private fun navigateToSuccessFragment(orderedItem: OrderedItem) {
        val bundle = Bundle().apply {
            putParcelable("orderedItem", orderedItem)
        }
        findNavController().navigate(
            R.id.action_makeOrderFragment_to_successOrderingFragment,
            bundle
        )
    }


    private fun updateOverallPrice() {
        val overallPrice = arguments?.getFloat("overallPrice") ?: 0.0F
        val sumOfOrderText = binding.sumOfOrder.text.toString()
        val overallText = "$sumOfOrderText $overallPrice тг"

        binding.sumOfOrder.text = overallText
    }

    private fun writeInfo() {
        val phoneNumber = binding.phoneNumber.text.toString().trim()
        val address = binding.address.text.toString().trim()
        val reference = binding.landmark.text.toString()
        val comments = binding.comments.text.toString()

        val isEmpty =
            phoneNumber.isEmpty() || address.isEmpty() || reference.isEmpty() || comments.isEmpty()

        binding.orderBtn.isEnabled = !isEmpty
        binding.orderBtn.backgroundTintList = resources.getColorStateList(
            if (isEmpty) R.color.durk_grey else R.color.main_green,
            context?.theme
        )
    }

    private fun setupTextChangeListeners() {
        binding.apply {
            phoneNumber.onTextChanged { writeInfo() }
            address.onTextChanged { writeInfo() }
            landmark.onTextChanged { writeInfo() }
            comments.onTextChanged { writeInfo() }
        }
    }

    private fun setupBackButton() {
        binding.arrowBack.setSafeOnClickListener {
            findNavController().popBackStack()
        }
    }
}