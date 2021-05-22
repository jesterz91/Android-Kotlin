package io.github.jesterz91.naverlogin.data

import com.google.gson.annotations.SerializedName

data class NaverLoginResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("response")
    val user: User,
    @SerializedName("resultcode")
    val resultCode: String
)

