package com.huahao.authenticator

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

object QRCodeGenerator {
    fun generate(entry: AuthEntry, size: Int = 512): Bitmap? {
        return try {
            val uri = buildOtpUri(entry)
            val hints = hashMapOf<EncodeHintType, Any>()
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            hints[EncodeHintType.MARGIN] = 1

            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(uri, BarcodeFormat.QR_CODE, size, size, hints)

            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            bitmap
        } catch (e: Exception) {
            null
        }
    }

    private fun buildOtpUri(entry: AuthEntry): String {
        val label = if (entry.issuer.isNotBlank() && entry.account.isNotBlank()) {
            "${entry.issuer}:${entry.account}"
        } else if (entry.issuer.isNotBlank()) {
            entry.issuer
        } else {
            entry.account
        }

        val params = listOf(
            "secret" to entry.secret,
            "algorithm" to entry.algorithm,
            "digits" to entry.digits.toString(),
            "period" to entry.period.toString()
        ).joinToString("&") { (k, v) -> "$k=$v" }

        return "otpauth://totp/${java.net.URLEncoder.encode(label, "UTF-8")}?$params"
    }
}
