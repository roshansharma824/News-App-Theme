package com.example.newsapptheme.presentation.screens.home

import com.example.newsapptheme.domain.usecase.UseCases
import androidx.compose.runtime.mutableStateOf
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
class HomeScreenViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    // MutableStateFlow to expose the state of the addNews operation
    private val _newsState = MutableStateFlow<Resource<Void?>>(Resource.loading(null))
    val newsState: StateFlow<Resource<Void?>> = _newsState

    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val contents = mutableStateOf(listOf( ""))
    val author =  mutableStateOf("")
    val urlToImage =  mutableStateOf("")
    val url =  mutableStateOf("")
    val publishedAt = mutableStateOf("")

    // Function to add news
    fun addNews(news: News) {
        viewModelScope.launch {
            useCases.addNewsUseCase.invoke(news)
                .onStart {
                    _newsState.value = Resource.loading(null) // Set loading state before collecting
                }
                .catch { exception ->
                    _newsState.value = Resource.error(null,exception.message ?: "Unknown Error") // Handle errors
                }
                .collect { resource ->
                    _newsState.value = resource // Update state based on emitted resource
                }
        }
    }

}