package cryptography.util

class MessageEncryptor {
    private val bitOperator = BitOperator()
    private val converter = Converter()

    fun encrypt(message: String, password: String): ByteArray {
        val messageBytes = message.toByteArray()
        val passwordBytes = password.toByteArray()
        return bitOperator.encrypt(messageBytes, passwordBytes)
    }

    fun decrypt(message: ByteArray, password: String): String {
        val passwordBytes = password.toByteArray()
        val decryptedBytes = bitOperator.decrypt(message, passwordBytes)
        return converter.byteArrayToCharString(decryptedBytes)
    }
}
