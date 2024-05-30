package com.petros.efthymiou.dailypulse.sources.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petros.efthymiou.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(private val useCase: SourcesUseCase): ViewModel() {

    private val _sourcesState =
        MutableStateFlow(SourcesState(listOf(), true, null))
    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    private fun getSources() {
        viewModelScope.launch {
            _sourcesState.emit(SourcesState(_sourcesState.value.sources, true, null))

            val sources = useCase.getSources()

            _sourcesState.emit(
                SourcesState(sources)
            )
        }
    }
}