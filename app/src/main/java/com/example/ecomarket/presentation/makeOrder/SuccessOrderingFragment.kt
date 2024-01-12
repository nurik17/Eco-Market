package com.example.ecomarket.presentation.makeOrder

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.ecomarket.R
import com.example.ecomarket.databinding.FragmentSuccessDilaogBinding
import com.example.ecomarket.utils.setSafeOnClickListener

class SuccessOrderingFragment : DialogFragment() {


    private var _binding : FragmentSuccessDilaogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessDilaogBinding.inflate(inflater,container,false)

        binding.btnToShop.setSafeOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(true)
            window?.setBackgroundDrawableResource(R.drawable.corner_dialog_bg)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}