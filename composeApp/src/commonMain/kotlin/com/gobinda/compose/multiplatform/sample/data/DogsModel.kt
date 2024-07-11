package com.gobinda.compose.multiplatform.sample.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogsModel(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String
)
