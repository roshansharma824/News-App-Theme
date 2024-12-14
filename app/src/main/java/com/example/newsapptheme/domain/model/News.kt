package com.example.newsapptheme.domain.model

data class News(
    var id:String? = null,
    val category: String? = null,
    val title: String? = null,
    val description: String? = null,
    val content: List<String>? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val url: String? = null,
    val publishedAt: String? = null,
)