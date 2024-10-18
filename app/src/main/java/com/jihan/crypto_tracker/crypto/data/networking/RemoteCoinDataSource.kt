package com.jihan.crypto_tracker.crypto.data.networking

import com.jihan.crypto_tracker.core.data.networking.constructUrl
import com.jihan.crypto_tracker.core.data.networking.safeCall
import com.jihan.crypto_tracker.core.domain.util.NetworkError
import com.jihan.crypto_tracker.core.domain.util.Result
import com.jihan.crypto_tracker.core.domain.util.map
import com.jihan.crypto_tracker.crypto.data.mappers.toCoin
import com.jihan.crypto_tracker.crypto.data.mappers.toCoinPrice
import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinHistoryResponseDto
import com.jihan.crypto_tracker.crypto.data.networking.dto.CoinResponseDto
import com.jihan.crypto_tracker.crypto.domain.Coin
import com.jihan.crypto_tracker.crypto.domain.CoinDataSource
import com.jihan.crypto_tracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource (private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }

    }


    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError> {

        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()


        return safeCall <CoinHistoryResponseDto> {

            httpClient.get(urlString = constructUrl("/assets/$coinId/history")){
                parameter("interval","h6")
                parameter("start",startMillis)
                parameter("end",endMillis)
            }
        }.map {response->
            response.data.map {
                it.toCoinPrice()
            }

        }

    }
}