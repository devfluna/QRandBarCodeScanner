package com.learning.qrbarcodescanner.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.qrbarcodescanner.utils.FakePackageList
import com.learning.qrbarcodescanner.ui.model.PackageDelivery

@Composable
fun PackagesScreen(
    packageList: List<PackageDelivery>,
    modifier: Modifier = Modifier,
    onStatusChanged: (PackageDelivery) -> Unit
) {
    Surface(modifier = modifier, color = MaterialTheme.colors.background) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
        ) {
            items(items = packageList, key = { it.id }) { singlePackage ->
                PackageCard(
                    modifier = modifier,
                    packageDelivery = singlePackage,
                    onStatusSelected = { status -> onStatusChanged(singlePackage.copy(status = status)) })
            }
        }
    }
}

@Preview
@Composable
fun PackagesScreenPreview() {
    PackagesScreen(
        modifier = Modifier.fillMaxSize(),
        packageList = FakePackageList
    ) {}
}