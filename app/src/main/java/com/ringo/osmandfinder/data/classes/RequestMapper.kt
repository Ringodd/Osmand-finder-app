package com.ringo.osmandfinder.data.classes

fun String.toBluetoothRequest(isFromArduino:Boolean): BluetoothRequest{
    val message = substringBeforeLast("#")
    val status = substringAfter("#")
    return BluetoothRequest(
        message = message,
        isSent = status.toBoolean(),
        isFromArduino = isFromArduino
    )
}

fun BluetoothRequest.toByteArray():ByteArray {
    return "$message#$isSent".encodeToByteArray()
}