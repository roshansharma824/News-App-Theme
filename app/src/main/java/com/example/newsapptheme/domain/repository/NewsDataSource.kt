package com.example.newsapptheme.domain.repository


import com.example.newsapptheme.domain.model.News
import com.example.newsapptheme.domain.model.Resource
import kotlinx.coroutines.flow.Flow


interface NewsDataSource {
    fun addNews(news: News): Flow<Resource<Void?>>
    fun getNews(): Flow<Resource<List<News>>>
    fun getNews(documentId:String): Flow<Resource<News>>
}