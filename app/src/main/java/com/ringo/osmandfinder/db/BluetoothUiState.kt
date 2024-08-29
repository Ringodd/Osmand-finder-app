package com.ringo.osmandfinder.db

import com.ringo.osmandfinder.bluetooth.domain.BluetoothDevice
import com.ringo.osmandfinder.data.classes.BluetoothRequest


data class BluetoothUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairedDevices: List<BluetoothDevice> = emptyList(),
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val errorMessage: String? = null,
    val messages: List<BluetoothRequest> = emptyList()
)