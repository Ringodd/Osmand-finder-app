package com.ringo.osmandfinder.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.ringo.osmandfinder.db.BluetoothUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(
    state:BluetoothUiState,
    onDisconnect: () -> Unit,
    onSendMessage: (String) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
    topBar = {
        TopAppBar(title = {
            Text(text = "Передача данных") },
            navigationIcon = {
                IconButton(onClick = onDisconnect) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        }
    ) {innerPadding ->
        var text by remember {
            mutableStateOf("")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

            if (state.messages.isNotEmpty()){
            item(state.messages.last()){

                RequestMessage(message = state.messages.last(), modifier = Modifier)
                }
            }else item {

                Text(text = "Сообщений нету")
                TextField(value = text, onValueChange = {text=it})
                Button(onClick = { onSendMessage(text) }) {
                    Text(text = "Отправить")
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

        }
    }
}