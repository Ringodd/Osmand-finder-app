package com.ringo.osmandfinder.widgets

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ringo.osmandfinder.bluetooth.domain.BluetoothDevice
import com.ringo.osmandfinder.bluetooth.domain.BluetoothDeviceDomain
import com.ringo.osmandfinder.db.BluetoothUiState

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { item->
        Column(Modifier.padding(item)){
            val context = LocalContext.current
        BluetoothDeviceList(
            pairedDevices = state.pairedDevices,
            scannedDevices = state.scannedDevices,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Button(onClick = onStartScan) {
                Text(text = "Start ")
            }
            Button(onClick = onStopScan) {
                Text(text = "Stop ")
                }
            }
        }
    }
}

@Composable
fun BluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {

        item {
            Text(
                text = "Подключенные",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(pairedDevices.filter { device -> device.name!!.startsWith("Osmand finder") }) { device ->
            var text by remember {
                mutableStateOf("")
            }
            var isPressed by remember {
                mutableStateOf(false)
            }
            val context = LocalContext.current
            Row(modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            Toast.makeText(context, "${device.name} - ${device.address}", Toast.LENGTH_SHORT).show()
                        },
                        onLongPress = {
                            isPressed = !isPressed
                            if (isPressed) {
                                text = device.address
                            } else text = ""
                        }
                    )
                }
                .padding(16.dp)) {
                Text(
                    text = device.name ?: "(No name)",
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                )
            }
        }

        item {
            Text(
                text = "Найденные",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(scannedDevices) { device ->
            var text by remember {
                mutableStateOf("")
            }
            var isPressed by remember {
                mutableStateOf(false)
            }
            val context = LocalContext.current
            Row(modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            Toast.makeText(context, "${device.name ?: "Test"} - ${device.address}", Toast.LENGTH_SHORT).show()
                        },
                        onLongPress = {
                            isPressed = !isPressed
                            if (isPressed) {
                                text = device.address
                            } else text = ""
                        }
                    )
                }
                .padding(16.dp)) {
                Text(
                    text = device.name ?: "(No name)",
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = text,
                )
            }
        }
    }
}