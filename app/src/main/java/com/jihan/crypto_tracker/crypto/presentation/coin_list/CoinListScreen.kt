package com.jihan.crypto_tracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.jihan.crypto_tracker.crypto.presentation.coin_list.components.CoinListItem
import com.jihan.crypto_tracker.crypto.presentation.coin_list.components.previewCoin
import com.jihan.crypto_tracker.crypto.presentation.models.CoinUi
import com.jihan.crypto_tracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.delay

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinListState,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {

            var text by remember { mutableStateOf("Loading") }

            LaunchedEffect(Unit) {
                while (true) {
                    text = "$text."
                    delay(300)
                    if (text == "Loading...") {
                        text = "Loading"
                    }
                }
            }


            Column {
                CircularProgressIndicator()
                Spacer(Modifier.height(3.dp))
                Text(text = text)
            }
        }
    } else {


        LazyColumn(modifier=Modifier.fillMaxSize()) {
            items(state.coins){coinUi ->

                CoinListItem(
                    coinUi = coinUi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)
                ) {

                }

                HorizontalDivider()

            }
        }

    }

}


@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    val state = CoinListState(
        coins = (0..100).map { previewCoin }
    )

    CryptoTrackerTheme {
        CoinListScreen(state = state)
    }
}