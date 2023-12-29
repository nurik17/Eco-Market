package com.example.ecomarket.presentation.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.data.entity.Resource
import com.example.ecomarket.databinding.FragmentProductBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.OffsetDecoration
import com.example.ecomarket.utils.setSafeOnClickListener
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class ProductFragment :
    BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter = ProductAdapter { clickableView, item ->
        onClick(clickableView, item)
    }
    private var selectedChip: Chip? = null

    override fun onBindView() {
        super.onBindView()

        setupViews()
        setupRecyclerView()
        getProductList()
        setupBackBtn()
    }

    private fun onClick(clickableView: ClickableView, item: ProductListItem) {
        when (clickableView) {
            ClickableView.ONCLICK -> navigationToBottomSheet(item)
            ClickableView.ONADDCLICK -> basketFunction(item)
        }
    }

    private fun basketFunction(item: ProductListItem) {
        viewModel.addProductToBasket(item)
        snackBarToAddedProducts(item)
    }

    private fun snackBarToAddedProducts(item: ProductListItem) {
        val snackbar =
            Snackbar.make(requireView(), item.title + " is added to basket", Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(),R.color.main_green))
        snackbar.show()
    }

    private fun navigationToBottomSheet(item: ProductListItem) {
        val bundle = Bundle()
        bundle.putParcelable("product", item)
        findNavController().navigate(R.id.action_productFragment_to_bottomProductFragment, bundle)
    }

    private fun setupViews() = with(binding) {
        val chipList = listOf(chip1, chip2, chip3, chip4, chip5, chip6, chip7)

        val chipClickListener: (Chip) -> Unit = { clickedChip ->
            productRv.adapter = productAdapter
            if (clickedChip == chip1) {
                productAdapter.submitList(viewModel.getProductList.value.data ?: emptyList())
                performSearchInCategory() // No chip selected, display all products
            } else {
                val selectedCategory = chipList.indexOf(clickedChip) + 1
                val productList = productListByCategory(selectedCategory)
                productAdapter.submitList(productList)
                performSearchInCategory(selectedCategory)
            }
            updateCheckedChip(clickedChip)
        }

        chipList.forEach { chip ->
            chip.setSafeOnClickListener {
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
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.addBtnBasket.visibility = View.GONE
                            binding.blockSearch.visibility = View.GONE
                            binding.blockChips.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            productAdapter.submitList(state.data ?: emptyList())
                            binding.progressBar.visibility = View.INVISIBLE
                            binding.addBtnBasket.visibility = View.VISIBLE
                            binding.blockSearch.visibility = View.VISIBLE
                            binding.blockChips.visibility = View.VISIBLE

                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.addBtnBasket.visibility = View.INVISIBLE
                            binding.blockSearch.visibility = View.INVISIBLE
                            binding.blockChips.visibility = View.INVISIBLE
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun performSearchInCategory(category: Int? = null) {
        binding.searchEdiText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val productList = if (category != null) {
                    productListByCategory(category)
                } else {
                    viewModel.getProductList.value.data ?: emptyList()
                }
                val filteredList = productList.filter { product ->
                    product.title.lowercase(Locale.getDefault())
                        .contains(query?.toString()?.lowercase(Locale.getDefault()) ?: "")
                }
                productAdapter.submitList(filteredList)
                if (filteredList.isEmpty()) {
                    showEmptyView()
                } else {
                    hideEmptyView()
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun showEmptyView() {
        binding.noResultImage.visibility = View.VISIBLE
        binding.noResultTv.visibility = View.VISIBLE
        binding.addBtnBasket.visibility = View.INVISIBLE
    }

    private fun hideEmptyView() {
        binding.noResultImage.visibility = View.INVISIBLE
        binding.noResultTv.visibility = View.INVISIBLE
        binding.addBtnBasket.visibility = View.VISIBLE
    }


    private fun setupBackBtn() {
        binding.icArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}



