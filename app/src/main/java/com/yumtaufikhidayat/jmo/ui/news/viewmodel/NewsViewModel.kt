package com.yumtaufikhidayat.jmo.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yumtaufikhidayat.jmo.data.NetworkResult
import com.yumtaufikhidayat.jmo.data.repository.JMORepository
import com.yumtaufikhidayat.jmo.model.news.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: JMORepository): ViewModel() {
    private val _getHeadlineNews: MutableLiveData<NetworkResult<NewsResponse>> = MutableLiveData()
    val getHeadlineNews: LiveData<NetworkResult<NewsResponse>> get() = _getHeadlineNews

    fun getHeadlineNews(sources: String) = viewModelScope.launch {
        repository.getHeadlineNews(sources).collect {
            _getHeadlineNews.value = it
        }
    }

    fun getEverythingNews() = repository.getEverythingNews().cachedIn(viewModelScope)
}