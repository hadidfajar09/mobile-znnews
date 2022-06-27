package com.hadiid.znnews.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.hadiid.znnews.databinding.CustomToolbarBinding
import com.hadiid.znnews.databinding.FragmentInfoBinding
import com.hadiid.znnews.util.PrefManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val infoModule = module {
    factory { InfoFragment() }
}

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: InfoViewModel by viewModel()
    private val pref by lazy { PrefManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingToolbar.title = viewModel.title

        binding.switch1.isChecked = pref.getBoolean("pref_is_dark")

        binding.switch1.setOnCheckedChangeListener { compoundButton, b ->
            when (b) {
                true -> {
                    pref.put("pref_is_dark",true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false -> {
                    pref.put("pref_is_dark",false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }


        binding.website.setOnClickListener {
            var url = "https://znnews.my.id"
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            })
        }

        binding.subtitleRate.setOnClickListener {
            var url = "https://play.google.com/store/apps/details?id=com.hadiid.znnews"
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            })
        }
    }


}