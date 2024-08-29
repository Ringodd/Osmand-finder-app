package com.ringo.osmandfinder.data.classes

import android.bluetooth.BluetoothSocket
import com.ringo.osmandfinder.bluetooth.domain.ConnectionResult
import com.ringo.osmandfinder.bluetooth.domain.TransferFailedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenForIncomingRequest():Flow<BluetoothRequest>{
        return flow {
            if (!socket.isConnected) {
                return@flow
            }
            val buffer = ByteArray(2048)
            while (true) {
                val byteCount = try {
                    socket.inputStream.read(buffer)
                } catch (e: IOException) {
                    throw TransferFailedException()
                }
                emit(
                    buffer.decodeToString(
                        endIndex = byteCount)
                        .toBluetoothRequest(isFromArduino = true)
                )

            }
        }
            .flowOn(Dispatchers.IO)
    }

    suspend fun sendRequest(bytes:ByteArray):Boolean{
        return withContext(Dispatchers.IO){
            try {
                socket.outputStream.write(bytes)
            } catch (e: IOException){
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }
}