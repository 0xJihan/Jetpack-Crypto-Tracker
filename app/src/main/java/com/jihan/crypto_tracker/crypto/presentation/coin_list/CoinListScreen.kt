package com.jihan.crypto_tracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.jihan.crypto_tracker.crypto.presentation.coin_list.components.CoinListItem
import com.jihan.crypto_tracker.crypto.presentation.coin_list.components.previewCoin
import com.jihan.crypto_tracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinListState,
    onAction : (CoinListAction) -> Unit
) {


    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {


       

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coinUi ->

                CoinListItem(
                    coinUi = coinUi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {
                    onAction(CoinListAction.OnCoinClick(coinUi))
                }

                HorizontalDivider()

            }
        }

    }

}


@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    val state = CoinListState(coins = (0..100).map { previewCoin })

    CryptoTrackerTheme {
        CoinListScreen(state = state){}
    }
}