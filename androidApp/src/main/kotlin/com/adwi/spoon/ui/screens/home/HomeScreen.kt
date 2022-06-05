package com.adwi.spoon.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
) {
    val recipes by viewModel.recipes.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = recipes) { recipe ->
            Text(text = recipe.title)
        }
    }
}
