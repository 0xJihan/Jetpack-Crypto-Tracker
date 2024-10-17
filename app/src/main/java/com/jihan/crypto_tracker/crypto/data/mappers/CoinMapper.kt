package com.jihan.crypto_tracker.crypto.data.mappers

import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinDto
import com.jihan.crypto_tracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {

    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd,
        marketCapUsd = marketCapUsd,
        changePercent24Hr = changePercent24Hr
    )

}