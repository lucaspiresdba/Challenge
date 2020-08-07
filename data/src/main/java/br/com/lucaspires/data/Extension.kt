package br.com.lucaspires.data

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.toMD5(): String {
    try {
        val digest = MessageDigest.getInstance("MD5")
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        val hexString = StringBuffer()
        for (i in messageDigest.indices) hexString.append(
            Integer.toHexString(
                0xFF and messageDigest[i].toInt()
            )
        )
        return hexString.toString()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}
