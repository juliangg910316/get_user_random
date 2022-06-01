package dev.julian.minitestdspot.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("results") val results: List<User>,
    @field:SerializedName("info") val info: InfoService
)
