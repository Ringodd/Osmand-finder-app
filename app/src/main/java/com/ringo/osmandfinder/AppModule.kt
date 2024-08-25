package com.ringo.osmandfinder

import android.content.Context
import com.ringo.osmandfinder.bluetooth.domain.BluetoothController
import com.ringo.osmandfinder.data.AndroidBluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
@Provides
@Singleton
fun provideBluetoothController(@ApplicationContext context: Context):BluetoothController{
    return AndroidBluetoothController(context)
    }
}