package com.learning.qrbarcodescanner.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.screen.components.PackageCard
import com.learning.qrbarcodescanner.utils.FakePackageList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PackagesScreen(
    packageList: List<PackageDelivery>,
    modifier: Modifier = Modifier,
    onStatusChanged: (PackageDelivery) -> Unit,
    onEvent: (DeliveryScreenEvent) -> Unit
) {
    Surface(modifier = modifier, color = MaterialTheme.colors.background) {

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { onEvent(DeliveryScreenEvent.SCAN) }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        }

                        IconButton(onClick = { onEvent(DeliveryScreenEvent.ADD) }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        }
                    }
                }
            }
        ) {
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
                    SwipeToDismiss(state = dismissState, background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Red
                            else -> Color.Transparent
                        }

                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(color)) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "",
                                modifier = Modifier.align(
                                    Alignment.CenterStart
                                )
                            )
                        }
                    }) {
                        PackageCard(
                            modifier = modifier,
                            packageDelivery = singlePackage,
                            onStatusSelected = { status -> onStatusChanged(singlePackage.copy(status = status)) })
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PackagesScreenPreview() {
    PackagesScreen(
        modifier = Modifier.fillMaxSize(),
        packageList = FakePackageList,
        onStatusChanged = {},
        onEvent = {}
    )
}