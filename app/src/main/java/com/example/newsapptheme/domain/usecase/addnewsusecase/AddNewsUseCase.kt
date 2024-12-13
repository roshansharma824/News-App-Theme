package com.example.newsapptheme.domain.usecase.addnewsusecase

import com.example.newsapptheme.data.repoitory.Repository
import com.example.newsapptheme.domain.model.News


class AddNewsUseCase(
    private val repository: Repository
) {
    operator fun invoke(news: News) = repository.addNews(news)
}