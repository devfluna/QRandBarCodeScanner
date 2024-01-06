package com.learning.qrbarcodescanner.ui.screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.model.PackageDelivery

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyDeliveryList(
    packageList: List<PackageDelivery>,
    modifier: Modifier = Modifier,
    onStatusChanged: (PackageDelivery) -> Unit,
    onEvent: (DeliveryScreenEvent) -> Unit
) {
    Surface(modifier = modifier, color = MaterialTheme.colors.background) {
        LazyColumn(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
        ) {
            items(items = packageList, key = { it.id }) { singlePackage ->

                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        val isSwipeToDelete = it == DismissValue.DismissedToEnd
                        if (isSwipeToDelete) {
                            onEvent(DeliveryScreenEvent.DELETE(singlePackage))
                        }

                        isSwipeToDelete
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = { DismissBackgroundIcons(dismissState = dismissState) }
                ) {
                    PackageCard(
                        modifier = modifier,
                        packageDelivery = singlePackage,
                        onStatusSelected = { status -> onStatusChanged(singlePackage.copy(status = status)) })
                }
            }
        }
    }
}