package com.huahao.authenticator

import android.util.Base64
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class TotpGenerator {
    companion object {
        @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
        fun generate(secret: String, timeStep: Long, digits: Int, algorithm: String): String {
            val key = Base64.decode(secret, Base64.NO_WRAP)
            val data = ByteArray(8)
            var value = timeStep
            for (i in 7 downTo 0) {
                data[i] = (value and 0xFF).toByte()
                value = value shr 8
            }

            val mac = Mac.getInstance(algorithm)
            mac.init(SecretKeySpec(key, algorithm))
            val hash = mac.doFinal(data)

            val offset = hash[hash.size - 1].toInt() and 0x0F
            val code = ((hash[offset].toInt() and 0x7F) shl 24) or
                    ((hash[offset + 1].toInt() and 0xFF) shl 16) or
                    ((hash[offset + 2].toInt() and 0xFF) shl 8) or
                    (hash[offset + 3].toInt() and 0xFF)

            var result = code % Math.pow(10.0, digits.toDouble()).toInt()
            return String.format("%0${digits}d", result)
        }

        fun getTimeStep(period: Int): Long {
            return System.currentTimeMillis() / 1000 / period
        }
    }
}