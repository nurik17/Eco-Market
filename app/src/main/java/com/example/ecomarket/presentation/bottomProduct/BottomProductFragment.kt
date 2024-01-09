package com.example.ecomarket.presentation.bottomProduct

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.ecomarket.R
import com.example.ecomarket.data.entity.ProductListItem
import com.example.ecomarket.databinding.ProductBottomFragmentBinding
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

        val productInfo : ProductListItem = when{
            Build.VERSION.SDK_INT >= 33 -> arguments?.getParcelable("product")!!
            else -> @Suppress("DEPRECATION") (arguments?.getParcelable("product")!!)
        }

        binding.productName.text = productInfo.title
        binding.productPrice.text = productInfo.price + " тг шт"
        binding.productDescription.text = productInfo.description
        Glide.with(binding.imageProduct)
            .load(productInfo.image)
            .error(R.drawable.ex_category)
            .into(binding.imageProduct)

        if(productInfo.quantity > 1){
            binding
        }
    }
}