package com.example.rapidreport.di

import com.example.rapidreport.data.remote.NewsApi
import com.example.rapidreport.data.remote.NewsApi.Companion.BASE_URL
import com.example.rapidreport.data.repository.NewsRepositoryImpl
import com.example.rapidreport.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton      // This means that it should create a single object for whole application, not separate objects everytime
    fun provideNewsApi(): NewsApi {
        // This is our retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())     // GsonConverterFactory helps to convert Json data to our Kotlin file
            .build()
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }
}