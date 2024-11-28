package com.example.feednoticias.presentation.portal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feednoticias.databinding.FragmentPortalBinding
import com.example.feednoticias.presentation.portal.viewmodel.PortalViewModel

class PortalFragment : Fragment() {

    private var _binding: FragmentPortalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val portalViewModel =
            ViewModelProvider(this).get(PortalViewModel::class.java)

        _binding = FragmentPortalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        portalViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}