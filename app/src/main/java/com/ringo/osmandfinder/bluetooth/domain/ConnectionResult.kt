package com.ringo.osmandfinder.bluetooth.domain

import com.ringo.osmandfinder.data.classes.BluetoothRequest

sealed interface ConnectionResult {
    object ConnectionEstablished: ConnectionResult
    data class Error(val message: String):ConnectionResult
    data class TransferSucceeded(val request:BluetoothRequest):ConnectionResult
}