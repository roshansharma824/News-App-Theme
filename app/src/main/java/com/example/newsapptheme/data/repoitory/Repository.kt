package com.example.newsapptheme.data.repoitory


import com.example.newsapptheme.domain.model.News
import com.example.newsapptheme.domain.repository.NewsDataSource
import javax.inject.Inject

//
class Repository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {
    fun addNews(news: News) = newsDataSource.addNews(news)

    fun getNews() = newsDataSource.getNews()

    fun getNews(documentId:String) = newsDataSource.getNews(documentId)

}