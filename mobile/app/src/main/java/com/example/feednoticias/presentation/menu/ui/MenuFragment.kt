package com.example.feednoticias.presentation.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.feednoticias.databinding.FragmentMenuBinding
import com.example.feednoticias.presentation.menu.ui.adapter.MenuAdapter
import com.example.feednoticias.presentation.menu.viewmodel.MenuViewModel

internal class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MenuViewModel
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        viewModelObserve()
        return binding.root
    }

    private fun viewModelObserve() {
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        viewModel.menuItems.observe(viewLifecycleOwner) { menuItems ->
            adapter = MenuAdapter(menuItems) { menuItem ->
                openWebView(menuItem.url)
            }
            binding.recyclerViewMenu.adapter = adapter
        }
    }

    private fun openWebView(url: String) {
        val action = MenuFragmentDirections.actionMenuFragmentToDetailsFragment(url)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}