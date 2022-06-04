package com.adwi.spoon.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.spoon.data.repository.SpoonRepository
import com.adwi.spoon.model.Recipe
import com.adwi.spoon.util.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: SpoonRepository,
) : ViewModel() {

    private var _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            repository.getRecipes(
                queries = mapOf("query" to "apple"),
                forceRefresh = true,
                onFetchSuccess = {
                    println("HomeViewModel - onFetchSuccess")
                },
                onFetchRemoteFailed = {
                    println("HomeViewModel - onFetchRemoteFailed")
                }
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        println("HomeViewModel - loading")
                    }
                    is DataState.Success -> {
                        println("HomeViewModel - success")
                        _recipes.value = dataState.data ?: kotlin.run {
                            println("HomeViewModel - success - emptyList")
                            emptyList()
                        }
                    }
                    is DataState.Error -> {
                        println("HomeViewModel - error - ${dataState.error?.message}")
                    }
                }
            }
        }
    }
}