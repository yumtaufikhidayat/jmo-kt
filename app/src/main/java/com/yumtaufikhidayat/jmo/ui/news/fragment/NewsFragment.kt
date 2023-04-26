package com.yumtaufikhidayat.jmo.ui.news.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.yumtaufikhidayat.jmo.R
import com.yumtaufikhidayat.jmo.data.NetworkResult
import com.yumtaufikhidayat.jmo.databinding.FragmentNewsBinding
import com.yumtaufikhidayat.jmo.ui.news.adapter.LoadMoreAdapter
import com.yumtaufikhidayat.jmo.ui.news.adapter.NewsAdapter
import com.yumtaufikhidayat.jmo.ui.news.viewmodel.NewsViewModel
import com.yumtaufikhidayat.jmo.utils.Common
import com.yumtaufikhidayat.jmo.utils.Common.convertDate
import com.yumtaufikhidayat.jmo.utils.Common.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel by viewModels<NewsViewModel>()
    private var newsAdapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeadlineNews()
        setNewsData()
        setNewsAdapter()
    }

    private fun setHeadlineNews() {
        binding.apply {
            newsViewModel.getHeadlineNews("bbc-news")
            newsViewModel.getHeadlineNews.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        val articles = it.data?.newsArticles?.get(0)
                        imgHeadlineNews.loadImage(articles?.urlToImage.orEmpty())
                        tvPublishedAt.text = articles?.publishedAt?.convertDate(Common.DATE_FORMAT_2).orEmpty()
                        tvNewsTitle.text = articles?.title.orEmpty()
                        cardHeadlineNews.setOnClickListener {
                            navigateToNewsDetail(articles?.url.orEmpty())
                        }
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    private fun setNewsData() {
        binding.apply {
            lifecycleScope.launch {
                newsAdapter = NewsAdapter {
                    navigateToNewsDetail(it.url)
                }

                newsViewModel.getEverythingNews().observe(viewLifecycleOwner) {
                    newsAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
                }

                newsAdapter?.apply {
                    addLoadStateListener { loadState ->
                        pbLoading.isVisible = loadState.source.refresh is LoadState.Loading
                        rvOtherNews.isVisible = loadState.source.refresh is LoadState.NotLoading
                        btnRetry.isVisible = loadState.source.refresh is LoadState.Error
                        tvError.isVisible = loadState.source.refresh is LoadState.Error

                        if (loadState.source.refresh is LoadState.NotLoading
                            && loadState.append.endOfPaginationReached
                            && itemCount < 1
                        ) {
                            rvOtherNews.isVisible = false
                            tvEmpty.isVisible = true
                        } else {
                            tvEmpty.isVisible = false
                        }
                    }

                    btnRetry.setOnClickListener {
                        this.retry()
                    }
                }
            }

            rvOtherNews.adapter = newsAdapter?.withLoadStateHeaderAndFooter(
                header = LoadMoreAdapter { newsAdapter?.retry() },
                footer = LoadMoreAdapter { newsAdapter?.retry() }
            )
        }
    }

    private fun navigateToNewsDetail(newsUrl: String?) {
        val bundle = bundleOf(
            NewsDetailFragment.EXTRA_NEWS_DETAIL to newsUrl.orEmpty()
        )
        findNavController().apply {
            popBackStack(R.id.newsFragment, true)
            navigate(R.id.newsDetailFragment, bundle)
        }
    }

    private fun setNewsAdapter() {
        binding.rvOtherNews.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = newsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}