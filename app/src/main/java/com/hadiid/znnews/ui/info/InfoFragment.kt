package com.hadiid.znnews.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.hadiid.znnews.R
import com.hadiid.znnews.databinding.CustomToolbarBinding
import com.hadiid.znnews.databinding.FragmentInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val infoModule = module {
    factory { InfoFragment() }
}

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: InfoViewModel by viewModel()

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



    }


}