package com.jihan.crypto_tracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val priceUsd : Double,
    val timestamp : Long
)
