package com.ringo.osmandfinder.bluetooth.domain

import com.ringo.osmandfinder.data.classes.BluetoothRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val isConnected:StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val pairedDevices: StateFlow<List<BluetoothDevice>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun startBluetoothServer():Flow<ConnectionResult>
    fun connectToDevice(device:BluetoothDevice):Flow<ConnectionResult>

    suspend fun trySendRequest(message:String): BluetoothRequest?

    fun closeConnection()

    fun release()
}