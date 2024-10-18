package com.jihan.crypto_tracker.crypto.data.mappers

import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinDto
import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinPriceDto
import com.jihan.crypto_tracker.crypto.domain.Coin
import com.jihan.crypto_tracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

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


fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.of("UTC"))
    )
}
