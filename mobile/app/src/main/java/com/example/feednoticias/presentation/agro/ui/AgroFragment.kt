package com.example.feednoticias.presentation.agro.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feednoticias.databinding.FragmentAgroBinding
import com.example.feednoticias.presentation.agro.viewmodel.AgroViewModel

class AgroFragment : Fragment() {

    private var _binding: FragmentAgroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val agroViewModel =
            ViewModelProvider(this).get(AgroViewModel::class.java)

        _binding = FragmentAgroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        agroViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}