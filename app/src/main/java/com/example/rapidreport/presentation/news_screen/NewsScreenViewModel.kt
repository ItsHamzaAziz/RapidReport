package com.example.rapidreport.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapidreport.domain.repository.NewsRepository
import com.example.rapidreport.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel      // Now it will knew that it is our view model and it will create an object itself
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var state by mutableStateOf(NewsScreenState())

    private var searchJob: Job? = null


    fun onEvent(event: NewsScreenEvent) {
        when (event) {
            // When user clicks on a category
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(category = state.category)
            }

            // When user clicks on a news card
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }

            // When user clicks on the search icon to search for a news
            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(
                    isSearchBarVisible = true,
                    articles = emptyList()
                )
            }

            // When user clicks on the close icon to close the search bar
            NewsScreenEvent.OnCloseIconClicked -> {
                state = state.copy(isSearchBarVisible = false)
                // Only take articles of particular category as user chose that particular category
                getNewsArticles(category = state.category)
            }

            // When user types in the search bar
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(1000L)
                    searchForNews(query = state.searchQuery)
                }
            }
        }
    }

    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)        // Show loading indicator when news are being fetched
            val result = newsRepository.getTopHeadlines(category = category)
            when (result) {
                // When the data was fetched successfully
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,              // Don't show loading indicator fetch is completed
                        error = null
                    )
                }

                // If there was any error while fetching data
                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),         // If there was any error do not show any news
                        isLoading = false,             // Don't show loading indicator when an error occurred
                        error = result.message
                    )
                }
            }
        }
    }

    // When user is using seqarch news functionality
    private fun searchForNews(query: String) {
        if (query.isEmpty()) {
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            // Search by query or the name the user enters (match that with data)
            val result = newsRepository.searchForNews(query = query)
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        articles = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}