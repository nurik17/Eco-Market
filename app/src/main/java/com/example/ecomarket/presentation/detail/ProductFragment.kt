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
import com.example.ecomarket.domain.ProductListItem
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
    private val productAdapter = ProductAdapter { clikcableView, item ->
        onClick(clikcableView, item)
    }
    private var selectedChip: Chip? = null
    private lateinit var chipList: List<Chip>

    override fun onBindView() {
        super.onBindView()

        setupViews()
        setupRecyclerView()
        getProductList()
        setupBackBtn()

        chipList.firstOrNull()?.let { selectCategory(it) }
    }

    private fun onClick(clickableView: ProductViewHolder.ClickableView, item: ProductListItem) {
        when (clickableView) {
            ProductViewHolder.ClickableView.ONCLICK -> navigationToBottomSheet(item)
            ProductViewHolder.ClickableView.ONADDCLICK -> snackBarToAddedProducts(item)
            ProductViewHolder.ClickableView.ONMINUSCLICK -> {
                viewModel.decrementProductQuantity(item)
            }

            ProductViewHolder.ClickableView.ONPLUSCLICK -> {
                viewModel.incrementProductQuantity(item)
            }
        }
    }

    private fun snackBarToAddedProducts(item: ProductListItem) {
        viewModel.incrementProductQuantity(item)
        val snackbar =
            Snackbar.make(requireView(), item.title + " is added to basket", Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.main_green))
        snackbar.show()
    }

    private fun navigationToBottomSheet(item: ProductListItem) {
        val bundle = Bundle()
        bundle.putParcelable("product", item)
        findNavController().navigate(R.id.action_productFragment_to_bottomProductFragment, bundle)
    }

    private fun setupViews() = with(binding) {
        chipList = listOf(chip1, chip2, chip3, chip4, chip5, chip6, chip7)
        val chipClickListener: (Chip) -> Unit = { clickedChip ->
            productRv.adapter = productAdapter
            selectCategory(clickedChip)
            updateCheckedChip(clickedChip)
        }
        chipList.forEach { chip ->
            chip.setSafeOnClickListener {
                chipClickListener.invoke(chip)
            }
        }
    }

    private fun selectCategory(selectedChip: Chip?) {
        if (selectedChip == null) {
            productAdapter.submitList(viewModel.getAllBasketProducts?.value ?: emptyList())
            performSearchInCategory()
        } else {
            if (selectedChip == binding.chip1) {
                productAdapter.submitList(viewModel.getAllBasketProducts?.value ?: emptyList())
                performSearchInCategory()
            } else {
                val selectedCategory = chipList.indexOf(selectedChip) + 1
                val productList = productListByCategory(selectedCategory)
                productAdapter.submitList(productList)
                performSearchInCategory(selectedCategory)

                if (productList.isEmpty()) showEmptyView()
                else hideEmptyView()
            }
            updateCheckedChip(selectedChip)
        }
    }

    private fun productListByCategory(category: Int) =
        viewModel.getAllBasketProducts?.value?.filter { it.category == category } ?: emptyList()

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
                            binding.noResultTv.visibility = View.GONE
                            binding.noResultImage.visibility = View.GONE
                        }

                        is Resource.Success -> {
                            viewModel.getAllBasketProducts?.observe(viewLifecycleOwner) { items ->
                                selectedChip?.let { selectCategory(it) }

                                if (items.isEmpty()) showEmptyView()
                                else hideEmptyView()
                            }
                            viewModel.addProductsToBasket(state.data ?: emptyList())
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
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun showEmptyView() {
        binding.noResultImage.visibility = View.VISIBLE
        binding.noResultTv.visibility = View.VISIBLE
    }

    private fun hideEmptyView() {
        binding.noResultImage.visibility = View.GONE
        binding.noResultTv.visibility = View.GONE
    }


    private fun setupBackBtn() {
        binding.icArrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}