package com.project.marvelapp.di

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.and
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = "${System.currentTimeMillis() / 1000}"
        val privateKey = "e759784d43a3d157c65a3e51be65770d8874ec1e"
        val publicKey = "9041df502c663cb02f6a091a82a8b9de"
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("hash", convertMD5(ts + privateKey + publicKey))
            .build()
        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private fun convertMD5(str: String): String {
        return try {
            val md: MessageDigest = MessageDigest.getInstance("MD5")
            md.update(str.toByteArray(charset("UTF-8")))
            val byteData: ByteArray = md.digest()
            val sb = StringBuffer()
            for (i in byteData.indices) sb.append(
                ((byteData[i] and 0xff) + 0x100).toString(16).substring(1)
            )
            sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            ""
        } catch (e: UnsupportedEncodingException) {
            ""
        }
    }
}