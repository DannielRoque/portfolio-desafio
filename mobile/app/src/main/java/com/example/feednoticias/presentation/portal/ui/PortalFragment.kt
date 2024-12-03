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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.feednoticias.data.entity.PortalViewData
import com.example.feednoticias.databinding.FragmentPortalBinding
import com.example.feednoticias.presentation.portal.ui.adapter.PortalAdapter
import com.example.feednoticias.presentation.portal.viewmodel.PortalViewModel
import com.example.feednoticias.presentation.portal.viewstate.PortalState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PortalFragment : Fragment() {

    private var _binding: FragmentPortalBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : PortalAdapter
    private val portalList: MutableList<PortalViewData> = mutableListOf()

    private val viewModel by viewModel<PortalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPortalBinding.inflate(inflater, container, false)
        viewModel.loadInitialFeed()
        observeFeed()
        setupAdapter()
        return binding.root
    }

    private fun observeFeed() {
        viewModel.feedState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PortalState.Error -> TODO()
                is PortalState.Loading -> showLoading(state.loading)
                is PortalState.Success -> updateUI(state.data)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        binding.loadingProgress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun updateUI(data: List<PortalViewData>) {
        portalList.clear()
        portalList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun setupAdapter() {
        adapter = PortalAdapter(portalList) { portalViewData ->
            portalViewData.url?.let { openWebView(it) }
        }
        binding.recyclerviewPortal.adapter = adapter

        observeScrollRecyclerview()
    }

    private fun observeScrollRecyclerview() = with(binding){
        recyclerviewPortal.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadNextPage()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun openWebView(url: String) {
//        val action = PortalFragmentDirections.actionPortalFragmentToDetailsFragment(url)
//        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}