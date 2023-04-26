package com.yumtaufikhidayat.jmo.ui.news.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private var newsUrl = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleData()
        setToolbar()
        setNewsDetail()
    }

    private fun getBundleData() {
        newsUrl = arguments?.getString(EXTRA_NEWS_DETAIL).orEmpty()
    }

    private fun setToolbar() {
        binding.toolbarNewsDetail.apply {
            imgBack.isVisible = true
            imgBack.setOnClickListener {
                findNavController().apply {
                    popBackStack(R.id.newsDetailFragment, true)
                    navigate(R.id.newsFragment)
                }
            }
            tvToolbar.text = getString(R.string.txt_news)
        }
    }

    private fun setNewsDetail() {
        binding.webViewNewsDetail.apply {
            settings.loadsImagesAutomatically = true
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.supportZoom()
            settings.builtInZoomControls = true
            settings.displayZoomControls = true
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = WebViewClient()
            loadUrl(newsUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_NEWS_DETAIL = "com.yumtaufikhidayat.jmo.ui.news.fragment.EXTRA_NEWS_DETAIL"
    }
}