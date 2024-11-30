package com.example.feednoticias.presentation.details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.feednoticias.R
import com.example.feednoticias.databinding.FragmentDetailsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

internal class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
        hideBottomNavWithAnimation()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showBottomNavWithAnimation()
                isEnabled = false
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() = with(binding) {
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()

        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
            }
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < PROGRESS_BAR_MAX) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }

        val url = arguments?.getString(PARAM_URL)
        url?.let {
            binding.webview.loadUrl(it)
        }
    }

    private fun hideBottomNavWithAnimation() {
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.animate()
            ?.translationY(bottomNav.height.toFloat())
            ?.setDuration(ANIMATION_DURATION)
            ?.withEndAction { bottomNav.visibility = View.GONE }
            ?.start()
    }

    private fun showBottomNavWithAnimation() {
        val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav?.apply {
            visibility = View.VISIBLE
            animate()
                .translationY(PARAM_TRANSLATION)
                .setDuration(ANIMATION_DURATION)
                .start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ANIMATION_DURATION = 300L
        const val PROGRESS_BAR_MAX = 100
        const val PARAM_URL = "url"
        const val PARAM_TRANSLATION = 0f
    }
}