package com.example.newsapptheme.domain.usecase.getnewsusecase

import com.example.newsapptheme.data.repoitory.Repository

class GetNewsUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.getNews()
}