package com.example.newsapptheme.domain.usecase.getdetailusecase

import com.example.newsapptheme.data.repoitory.Repository

class GetNewsDetailUseCase(
    private val repository: Repository
) {
    operator fun invoke(documentId: String) = repository.getNews(documentId)
}