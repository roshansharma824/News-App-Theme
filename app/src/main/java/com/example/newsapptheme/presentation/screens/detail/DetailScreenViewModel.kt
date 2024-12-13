package com.example.newsapptheme.presentation.screens.detail

import com.example.newsapptheme.domain.usecase.UseCases
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapptheme.domain.model.News
import com.example.newsapptheme.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {


    // Initialize _newsState as a MutableStateFlow with a default Resource.Loading state
    private val _newsState = MutableStateFlow<Resource<News>?>(Resource.loading(null))
    val newsState: StateFlow<Resource<News>?> = _newsState

    // Function to get the news list
    fun getNews(documentId: String) {
        viewModelScope.launch {
            useCases.getNewsDetailUseCase.invoke(documentId)
                .onStart {
//                    _newsState.value = Resource.loading(null) // Set loading state before data is emitted
                }
                .catch { exception ->
                    _newsState.value = Resource.error(null,exception.message ?: "Unknown error") // Handle exceptions
                }
                .collect { resource ->
                    _newsState.value = resource // Update the state with the emitted resource
                }
        }
    }
}