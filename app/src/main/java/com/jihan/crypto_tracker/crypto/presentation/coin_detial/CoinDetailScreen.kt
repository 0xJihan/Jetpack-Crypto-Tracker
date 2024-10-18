@file:OptIn(ExperimentalLayoutApi::class)

package com.jihan.crypto_tracker.crypto.presentation.coin_detial

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.crypto_tracker.R
import com.jihan.crypto_tracker.crypto.presentation.coin_detial.component.InfoCard
import com.jihan.crypto_tracker.crypto.presentation.coin_list.CoinListState
import com.jihan.crypto_tracker.crypto.presentation.coin_list.components.previewCoin
import com.jihan.crypto_tracker.crypto.presentation.models.toDisplayableNumber
import com.jihan.crypto_tracker.ui.theme.CryptoTrackerTheme
import com.jihan.crypto_tracker.ui.theme.greenBackground

@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {


    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {

        val coin = state.selectedCoin


        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp)
            )

            Text(
                text = coin.name,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                color = contentColor

            )
            Text(
                text = coin.symbol,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = contentColor

            )


            FlowRow(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {

                InfoCard(
                    title = stringResource(R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.formatted}",
                    icon = ImageVector.vectorResource(R.drawable.stock),
                )


                InfoCard(
                    title = stringResource(R.string.price),
                    formattedText = "$ ${coin.priceUsd.formatted}",
                    icon = ImageVector.vectorResource(coin.iconRes),
                )

                val absoluteChangeFormatted =
                    (coin.priceUsd.value * (coin.changePercent24Hr.value) / 100).toDisplayableNumber()
                val isPositiveChange = coin.changePercent24Hr.value > 0

                val priceContentColor = if (isPositiveChange) {
                    if (isSystemInDarkTheme()) Color.Green else greenBackground
                } else {
                    MaterialTheme.colorScheme.error
                }


                InfoCard(
                    title = stringResource(R.string.price_change_24h),
                    formattedText = absoluteChangeFormatted.formatted,
                    icon = if (isPositiveChange) ImageVector.vectorResource(R.drawable.trending)
                    else ImageVector.vectorResource(R.drawable.trending_down),
                    contentColor = priceContentColor
                )


            }


        }


    }
}


@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {

    CryptoTrackerTheme {


        CoinDetailScreen(
            state = CoinListState(
                selectedCoin = previewCoin
            ), modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}