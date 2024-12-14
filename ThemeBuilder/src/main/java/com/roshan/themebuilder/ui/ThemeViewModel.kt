package com.roshan.themebuilder.ui

import com.roshan.themebuilder.data.ColorSchema
import com.roshan.themebuilder.data.DataSource
import com.roshan.themebuilder.data.PlayText
import com.roshan.themebuilder.data.ResultState
import android.app.Application
import android.content.Context
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.roshan.themebuilder.R
import com.roshan.themebuilder.data.PlayCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File


class ThemeViewModel(application: Application) : AndroidViewModel(application = application) {

    private val appContext: Context = getApplication()
    private val dataSource = DataSource()

    // Initialize _newsState as a MutableStateFlow with a default Resource.Loading state
    private val _textDisplayMediumState =
        MutableStateFlow<ResultState<PlayText>>(ResultState.Loading)
    val textDisplayMediumState: StateFlow<ResultState<PlayText>> = _textDisplayMediumState

    private val _textBodyMediumState = MutableStateFlow<ResultState<PlayText>>(ResultState.Loading)
    val textBodyMediumState: StateFlow<ResultState<PlayText>> = _textBodyMediumState
    private val _textHeadlineMediumState = MutableStateFlow<ResultState<PlayText>>(ResultState.Loading)
    val textHeadlineMediumState: StateFlow<ResultState<PlayText>> = _textHeadlineMediumState

    private val _textLabelMediumState = MutableStateFlow<ResultState<PlayText>>(ResultState.Loading)
    val textLabelMediumState: StateFlow<ResultState<PlayText>> = _textLabelMediumState

    private val _textHeadlineSmallState = MutableStateFlow<ResultState<PlayText>>(ResultState.Loading)
    val textHeadlineSmallState: StateFlow<ResultState<PlayText>> = _textHeadlineSmallState

    private val _colorsLightState = MutableStateFlow<ResultState<ColorSchema>>(ResultState.Loading)
    val colorsLightState: StateFlow<ResultState<ColorSchema>> = _colorsLightState

    private val _cardState = MutableStateFlow<ResultState<PlayCard>>(ResultState.Loading)
    val cardState: StateFlow<ResultState<PlayCard>> = _cardState

    private val _postJsonState = MutableStateFlow<ResultState<String>>(ResultState.Loading)
    val postJsonState: StateFlow<ResultState<String>> = _postJsonState

    init {
        println("ThemeViewModel init called")
        postThemeJson()
        getTextDisplayMedium()
        getTextHeadlineSmall()
        getTextBodyMedium()
        getTextLabelMedium()
        getColorsLight()
        getCardData()
        getTextHeadlineMedium()
    }

    private fun getCardData() {
        viewModelScope.launch {
            dataSource.getCardData().collect {
                _cardState.value = it
            }
        }
    }

    private fun postThemeJson() {
        viewModelScope.launch {
            // Step 1: Load the JSON string from raw resources
            val jsonString = try {
                val inputStream = appContext.resources.openRawResource(R.raw.theme) // Replace `appContext` with your context provider
                inputStream.bufferedReader().use { it.readText() }
            } catch (e: Exception) {
                _postJsonState.value = ResultState.Failure(e)
                return@launch
            }

            // Step 2: Post the JSON to Firebase
            dataSource.postThemeJson(jsonString).collect {
                _postJsonState.value = it
            }
        }
    }

    private fun getTextDisplayMedium() {
        viewModelScope.launch {
            dataSource.getTextDisplayMedium().collect {
                _textDisplayMediumState.value = it
            }
        }
    }

    private fun getTextBodyMedium() {
        viewModelScope.launch {
            dataSource.getTextBodyMedium().collect {
                _textBodyMediumState.value = it
            }
        }
    }
    private fun getTextHeadlineMedium() {
        viewModelScope.launch {
            dataSource.getTextHeadlineMedium().collect {
                _textHeadlineMediumState.value = it
            }
        }
    }

    private fun getTextLabelMedium() {
        viewModelScope.launch {
            dataSource.getTextLabelMedium().collect {
                _textLabelMediumState.value = it
            }
        }
    }

    private fun getTextHeadlineSmall() {
        viewModelScope.launch {
            dataSource.getTextHeadlineSmall().collect {
                _textHeadlineSmallState.value = it
            }
        }
    }

    private fun getColorsLight() {
        viewModelScope.launch {
            dataSource.getColorsLight().collect {
                _colorsLightState.value = it
            }
        }
    }

    override fun onCleared() {
        println("ThemeViewModel onCleared called")
        super.onCleared()
    }
}