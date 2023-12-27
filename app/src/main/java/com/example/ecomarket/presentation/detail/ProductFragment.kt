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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val viewModel: ProductViewModel by viewModels()

    private val productAdapter = ProductAdapter { item -> }

    private val productListItem: List<ProductListItem>? = null

    override fun onBindView() {
        super.onBindView()

        setupRecyclerView()
        getProductList()
        setupBackBtn()

    }

    private fun setupRecyclerView() {
        binding.productRv.apply {
            layoutManager =
                GridLayoutManager(
                    requireContext(),
                    2, GridLayoutManager.VERTICAL, false
                )
            adapter = productAdapter
            addItemDecoration(OffsetDecoration(start = 15, bottom = 10))
        }
    }

    private fun getProductList() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getProductList.collect { state ->
                    when (state) {
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            val categoryList = state.data ?: emptyList()
                            productAdapter.setData(categoryList)
                            binding.progressBar.visibility = View.INVISIBLE
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }

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
