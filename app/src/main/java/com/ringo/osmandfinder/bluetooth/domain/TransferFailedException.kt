package com.ringo.osmandfinder.bluetooth.domain

import java.io.IOException

class TransferFailedException:IOException("Reading incoming data failed") {
}