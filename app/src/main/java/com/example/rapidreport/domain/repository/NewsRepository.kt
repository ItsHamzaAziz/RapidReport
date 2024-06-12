package com.example.rapidreport.domain.repository

import com.example.rapidreport.domain.model.Article
import com.example.rapidreport.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<Article>>


    suspend fun searchForNews(
        query: String
    ): Resource<List<Article>>
}