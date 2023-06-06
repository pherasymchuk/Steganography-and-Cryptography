package cryptography.util

import kotlin.experimental.xor

class BitOperator {
    fun encrypt(message: ByteArray, password: ByteArray): ByteArray {
        val encrypted = ByteArray(message.size)
        for (i in message.indices) {
            val passwordByte = password[i % password.size]
            encrypted[i] = (message[i] xor passwordByte)
        }
        return encrypted
    }

    fun decrypt(message: ByteArray, password: ByteArray): ByteArray = encrypt(message, password)
}
