package com.example.ecomarket.presentation.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.databinding.FragmentProductBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment :
    BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter = ProductAdapter { item -> }

    private var selectedChip: Chip? = null

    override fun onBindView() {
        super.onBindView()

        setupViews()
        setupRecyclerView()
        getProductList()
        setupBackBtn()
    }

    private fun setupViews() = with(binding) {
        val chipList = listOf(chip1, chip2, chip3, chip4, chip5, chip6, chip7)

        val chipClickListener: (Chip) -> Unit = { clickedChip ->
            productRv.adapter = productAdapter

            if (clickedChip == chip1) {
                productAdapter.submitList(viewModel.getProductList.value?.data ?: emptyList())
            } else {
                val selectedCategory = chipList.indexOf(clickedChip) + 1
                val productList = productListByCategory(selectedCategory)
                productAdapter.submitList(productList)
            }

            updateCheckedChip(clickedChip)
        }

        chipList.forEach { chip ->
            chip.setOnClickListener {
                chipClickListener.invoke(chip)
            }
        }
    }

    private fun productListByCategory(category: Int) =
        viewModel.getProductList.value.data?.filter { it.category == category } ?: emptyList()

    private fun updateCheckedChip(clickedChip: Chip) {
        selectedChip?.isChecked = false
        selectedChip = clickedChip
        selectedChip?.isChecked = true
    }

    private fun setupRecyclerView() = with(binding.productRv) {
        layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        adapter = productAdapter
        addItemDecoration(OffsetDecoration(start = 15, bottom = 10))
    }

    private fun getProductList() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getProductList.collect { state ->
                    when (state) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            productAdapter.submitList(state.data ?: emptyList())
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                        is Resource.Error -> binding.progressBar.visibility = View.GONE
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setupBackBtn() {
        binding.icArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
