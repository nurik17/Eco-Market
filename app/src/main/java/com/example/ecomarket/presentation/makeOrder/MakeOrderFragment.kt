package com.example.ecomarket.presentation.makeOrder

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.OrderRequest
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.data.entity.Product
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.databinding.FragmentMakeOrderBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.onTextChanged
import com.example.ecomarket.utils.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

        binding.orderBtn.setSafeOnClickListener {
            makeOrder()
        }

    }

    private fun makeOrder() {
        val phoneNumber = binding.phoneNumber.text.toString()
        val address = binding.address.text.toString()
        val reference = binding.landmark.text.toString()
        val comments = binding.comments.text.toString()


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

        val isEmpty = phoneNumber.isEmpty() || address.isEmpty()

        binding.orderBtn.isEnabled = !isEmpty
        binding.orderBtn.backgroundTintList = resources.getColorStateList(
            if (isEmpty) R.color.durk_grey else R.color.main_green,
            context?.theme
        )
    }

    private fun setupTextChangeListeners() {
        binding.phoneNumber.onTextChanged { writeInfo() }
        binding.address.onTextChanged { writeInfo() }
    }

}