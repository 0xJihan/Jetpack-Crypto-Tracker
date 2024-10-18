package com.jihan.crypto_tracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryResponseDto(
    val data : List<CoinPriceDto>
)
