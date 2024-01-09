package com.example.ecomarket.presentation.info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import com.example.ecomarket.R
import com.example.ecomarket.databinding.FragmentInformationBinding
import com.example.ecomarket.utils.BaseFragment
import com.example.ecomarket.utils.setSafeOnClickListener

class InformationFragment :
    BaseFragment<FragmentInformationBinding>(FragmentInformationBinding::inflate) {
    override fun onBindView() {
        super.onBindView()

        val phoneNumber = getString(R.string.phone_number)
        val phoneNumberWhatsapp = getString(R.string.phone_number_whatsapp)
        val profileName = getString(R.string.instagram_profile_name)
        binding.mainText.text = getString(R.string.info_page_main_text)

        navigateToPhone(phoneNumber)
        navigateToWhatsapp(phoneNumberWhatsapp)
        navigateToInstagram(profileName)
    }

    private fun navigateToPhone(phoneNumber: String) {
        binding.icCallBlock.setSafeOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
            activity?.startActivity(dialIntent)
        }
    }

    private fun navigateToWhatsapp(phoneNumber: String) {
        binding.icWhatsappBlock.setSafeOnClickListener {
            val message = getString(R.string.whatsapp_message)
            val uri = Uri.parse(WHATSAPP_URI + phoneNumber + WHATSAPP_URI_TEXT + message)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    private fun navigateToInstagram(profileName: String) {
        binding.icInstagramBlock.setSafeOnClickListener {
            val uri = Uri.parse("$INSTAGRAM_URI/$profileName")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }


    private companion object {
        const val WHATSAPP_URI = "https://api.whatsapp.com/send?phone="
        const val WHATSAPP_URI_TEXT = "&text="
        const val INSTAGRAM_URI = "http://instagram.com/_u"
    }
}