package com.dfavilav.marvelapplication.presentation.screens.comic

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dfavilav.marvelapplication.domain.model.comic.Comic
import com.dfavilav.marvelapplication.domain.model.hero.Hero
import com.dfavilav.marvelapplication.domain.use_cases.UseCases
import com.dfavilav.marvelapplication.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _allComics = MutableStateFlow<PagingData<Comic>>(PagingData.empty())
    val allComics: StateFlow<PagingData<Comic>> = _allComics

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val characterId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            characterId?.let { id ->
                useCases.getAllComicsUseCase(id).collectLatest { pagingData ->
                    _allComics.value = pagingData
                }
            }
        }
    }
}
