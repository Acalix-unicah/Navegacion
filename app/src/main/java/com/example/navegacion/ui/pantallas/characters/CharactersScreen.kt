package com.example.navegacion.ui.pantallas.characters

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.navegacion.viewmodels.characters.CharacterViewModel

@Composable
fun CharacterScreen(viewModel: CharacterViewModel = viewModel()) {

    val characters by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCharacters()
    }

    LazyColumn {
        items(characters) { char ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = char.image,
                    contentDescription = char.name,
                    modifier = Modifier.size(80.dp)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    Text(char.name, fontSize = 20.sp)
                    Text(char.species)
                    Text(char.status)
                }
            }
        }
    }
}