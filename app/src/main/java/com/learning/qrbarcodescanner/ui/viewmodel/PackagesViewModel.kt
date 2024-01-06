package com.learning.qrbarcodescanner.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.usecases.DeletePackageUseCase
import com.learning.qrbarcodescanner.ui.usecases.GetAllPackagesUseCase
import com.learning.qrbarcodescanner.ui.usecases.InsertPackageUseCase
import com.learning.qrbarcodescanner.ui.usecases.UpdatePackageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PackagesViewModel @Inject constructor(
    private val getAllPackagesUseCase: GetAllPackagesUseCase,
    private val insertPackageUseCase: InsertPackageUseCase,
    private val updatePackageUseCase: UpdatePackageUseCase,
    private val deletePackageUseCase: DeletePackageUseCase
) : ViewModel() {

    init {
//        insertDummyData()
    }

    val allPackagesList: Flow<List<PackageDelivery>> = getAllPackagesUseCase()

    private fun insertDummyData() {
        viewModelScope.launch {
            val dummy = PackageDelivery(
                id = Random.nextInt(),
                itemName = Random.nextInt().toString(),
                trackingNumber = Random.nextInt().toString(),
                status = DeliveryStatus.Shipped()
            )
            insertPackageUseCase.insert(dummy)
        }
    }

    fun insertNew(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            insertPackageUseCase.insert(delivery)
        }
    }

    fun update(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePackageUseCase.update(delivery)
        }
    }

    fun delete(delivery: PackageDelivery) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePackageUseCase.delete(delivery)
        }
    }
}