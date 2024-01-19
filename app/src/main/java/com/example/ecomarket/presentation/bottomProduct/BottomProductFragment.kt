package com.example.ecomarket.presentation.bottomProduct

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.domain.ProductListItem
import com.example.ecomarket.databinding.ProductBottomFragmentBinding
import com.example.ecomarket.utils.getProductItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomProductFragment : BottomSheetDialogFragment() {

    private var _binding : ProductBottomFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductBottomFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productInfo: ProductListItem = arguments.getProductItem("product")
        getInfoAboutProduct(productInfo)


    }

    @SuppressLint("SetTextI18n")
    private fun getInfoAboutProduct(productInfo: ProductListItem){
        binding.apply {
            productName.text = productInfo.title
            productPrice.text = productInfo.price + " тг шт"
            productDescription.text = productInfo.description
            Glide.with(imageProduct)
                .load(productInfo.image)
                .error(R.drawable.ex_category)
                .into(imageProduct)
        }
    }
}