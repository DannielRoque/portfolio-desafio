package com.example.feednoticias.presentation.portal.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feednoticias.databinding.FragmentPortalBinding
import com.example.feednoticias.presentation.portal.viewmodel.PortalViewModel
import com.example.feednoticias.presentation.portal.viewstate.PortalState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PortalFragment : Fragment() {

    private var _binding: FragmentPortalBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<PortalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalBinding.inflate(inflater, container, false)
        viewModel.loadInitialFeed()
        observeFeed()
        return binding.root
    }

    private fun observeFeed() {
        viewModel.feedState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PortalState.Error -> TODO()
                is PortalState.Loading -> TODO()
                is PortalState.Success -> Log.e("Daniel ----->", "Retorno -> ${state.data}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}