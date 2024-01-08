package com.learning.qrbarcodescanner.ui.navigation

sealed class DeliveryScreen(val route: String){
    object HOME: DeliveryScreen("HOME_SCREEN")
    object ADD: DeliveryScreen("ADD_SCREEN")
}
