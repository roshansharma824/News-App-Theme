package com.example.newsapptheme.domain.usecase

import com.example.newsapptheme.domain.usecase.addnewsusecase.AddNewsUseCase
import com.example.newsapptheme.domain.usecase.getdetailusecase.GetNewsDetailUseCase
import com.example.newsapptheme.domain.usecase.getnewsusecase.GetNewsUseCase

data class UseCases(
    val addNewsUseCase: AddNewsUseCase,
    val getNewsUseCase: GetNewsUseCase,
    val getNewsDetailUseCase: GetNewsDetailUseCase
)
