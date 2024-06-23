package com.example.rapidreport.presentation.news_screen

import com.example.rapidreport.domain.model.Article

sealed class NewsScreenEvent {
    // When we need some value with user section then we use data class
    data class OnNewsCardClicked(var article: Article) : NewsScreenEvent()
    data class OnCategoryChanged(var category: String) : NewsScreenEvent()
    data class OnSearchQueryChanged(var searchQuery: String) : NewsScreenEvent()

    // When we don't need any value with user section then we use object
    object OnSearchIconClicked: NewsScreenEvent()
    object OnCloseIconClicked: NewsScreenEvent()
}
