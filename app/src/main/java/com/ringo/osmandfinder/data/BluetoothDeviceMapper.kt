package com.ringo.osmandfinder.data

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.ringo.osmandfinder.bluetooth.domain.BluetoothDeviceDomain


@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain():BluetoothDeviceDomain{
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}