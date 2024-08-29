package com.ringo.osmandfinder.data.classes

fun String.toBluetoothRequest(isFromLocalUser: Boolean): BluetoothRequest {
    val name = substringBeforeLast("#")
    val message = substringAfter("#")
    return BluetoothRequest(
        message = message,
        senderName = name,
        isFromLocalUser = isFromLocalUser
    )
}

fun BluetoothRequest.toByteArray(): ByteArray {
    return "$senderName#$message".encodeToByteArray()
}