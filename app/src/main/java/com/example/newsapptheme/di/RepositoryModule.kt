package com.example.newsapptheme.di


import com.example.newsapptheme.data.repoitory.Repository
import com.example.newsapptheme.domain.usecase.UseCases
import com.example.newsapptheme.domain.usecase.addnewsusecase.AddNewsUseCase
import com.example.newsapptheme.domain.usecase.getdetailusecase.GetNewsDetailUseCase
import com.example.newsapptheme.domain.usecase.getnewsusecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UseCases {
        return UseCases(
            addNewsUseCase = AddNewsUseCase(repository),
            getNewsUseCase = GetNewsUseCase(repository),
            getNewsDetailUseCase = GetNewsDetailUseCase(repository)
        )
    }

}