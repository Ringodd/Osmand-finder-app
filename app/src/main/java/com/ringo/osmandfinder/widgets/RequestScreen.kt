package com.ringo.osmandfinder.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ringo.osmandfinder.data.classes.BluetoothRequest




@Composable
fun RequestMessage(
    message: BluetoothRequest,
    modifier: Modifier = Modifier
) {
    Box() {
        if (message.isFromArduino){
            Text(text = message.message)
        }
    }
}

@Preview
@Composable
fun RequestPreview() {
    RequestMessage(message =
        BluetoothRequest(
            message = "Hello World!",
            isSent = true,
            isFromArduino = true
        )
    )
}