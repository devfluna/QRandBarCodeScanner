package com.learning.qrbarcodescanner.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.screen.components.BottomDeliveryActionBar
import com.learning.qrbarcodescanner.ui.screen.components.LazyDeliveryList
import com.learning.qrbarcodescanner.utils.FakePackageList

@Composable
fun PackagesScreen(
    packageList: List<PackageDelivery>,
    modifier: Modifier = Modifier,
    onStatusChanged: (PackageDelivery) -> Unit,
    onEvent: (DeliveryScreenEvent) -> Unit
) {
    Scaffold(
        bottomBar = { BottomDeliveryActionBar(onEvent = onEvent) }
    ) {
        LazyDeliveryList(
            modifier = modifier,
            packageList = packageList,
            onStatusChanged = onStatusChanged,
            onEvent = onEvent)
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