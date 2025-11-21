package com.example.navegacion.viewmodels.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navegacion.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.navegacion.data.model.Character

class CharacterViewModel : ViewModel() {

    private val repo = CharacterRepository()

    private val _state = MutableStateFlow<List<Character>>(emptyList())
    val state: StateFlow<List<Character>> = _state

    fun loadCharacters() {
        viewModelScope.launch {
            try {
                val response = repo.getCharacters()
                _state.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}