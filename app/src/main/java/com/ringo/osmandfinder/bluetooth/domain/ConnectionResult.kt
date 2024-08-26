package com.ringo.osmandfinder.bluetooth.domain

sealed interface ConnectionResult {
    object ConnectionEstablished: ConnectionResult
    data class Error(val message: String):ConnectionResult
}