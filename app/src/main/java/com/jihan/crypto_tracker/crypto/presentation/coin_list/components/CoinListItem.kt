package com.jihan.crypto_tracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jihan.crypto_tracker.crypto.domain.Coin
import com.jihan.crypto_tracker.crypto.presentation.models.CoinUi
import com.jihan.crypto_tracker.crypto.presentation.models.toCoinUi
import com.jihan.crypto_tracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coinUi: CoinUi,
    onItemCLick: () -> Unit,
) {

    val contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Row(
        modifier = modifier.clickable {
            onItemCLick()
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Icon(
            imageVector = ImageVector.vectorResource(coinUi.iconRes),
            contentDescription = coinUi.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )


        Column(Modifier.weight(1f)) {

            Text(
                text = coinUi.symbol,
                color = contentColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = coinUi.name,
                color = contentColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )

        }

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "$ ${coinUi.priceUsd.formatted}",
                color = contentColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(5.dp))

            PriceChange(
                change = coinUi.changePercent24Hr
            )

        }

    }

}


@PreviewLightDark
@Composable
private fun CoinListItemPreview() {

    CryptoTrackerTheme {
        CoinListItem(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.background
            ), coinUi = previewCoin
        ) { }
    }

}

internal val previewCoin = Coin(

    id = "bitcoin",
    rank = 1,
    symbol = "BTC",
    name = "Bitcoin",
    marketCapUsd = 119150835874.4699281625807300,
    priceUsd = 6929.8217756835584756,
    changePercent24Hr = -0.8101417214350335,

    ).toCoinUi()