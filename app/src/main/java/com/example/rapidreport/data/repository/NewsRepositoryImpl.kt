package com.example.rapidreport.data.repository

import com.example.rapidreport.data.remote.NewsApi
import com.example.rapidreport.domain.model.Article
import com.example.rapidreport.domain.repository.NewsRepository
import com.example.rapidreport.util.Resource

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {

    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try {
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(data = response.articles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun searchForNews(query: String): Resource<List<Article>> {
        return try {
            val response = newsApi.searchForNews(query = query)
            Resource.Success(data = response.articles)
        } catch (e: Exception) {
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}