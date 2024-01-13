package com.learning.qrbarcodescanner.di

import android.app.Application
import androidx.room.Room
import com.learning.qrbarcodescanner.data.database.DeliveryDatabase
import com.learning.qrbarcodescanner.data.database.DeliveryDao
import com.learning.qrbarcodescanner.data.repository.DeliveryRepositoryImpl
import com.learning.qrbarcodescanner.data.repository.DeliveryRepository
import com.learning.qrbarcodescanner.data.usecases.*
import com.learning.qrbarcodescanner.ui.usecases.*
import com.learning.qrbarcodescanner.ui.viewmodel.PackagesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(app: Application): DeliveryDatabase {
        return Room.databaseBuilder(
            app,
            DeliveryDatabase::class.java,
            "delivery_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDeliveryDao(database: DeliveryDatabase): DeliveryDao {
        return database.packageDeliveryDao()
    }

    @Provides
    @Singleton
    fun providesDeliveryRepository(packageDeliveryDao: DeliveryDao): DeliveryRepository =
        DeliveryRepositoryImpl(packageDeliveryDao)

    @Provides
    @Singleton
    fun providesGetAllPackagesUseCase(repository: DeliveryRepository): GetAllPackagesUseCase =
        GetAllPackagesUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesInsertPackageUseCase(repository: DeliveryRepository): InsertPackageUseCase =
        InsertPackageUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesUpdatePackageUseCase(repository: DeliveryRepository): UpdatePackageUseCase =
        UpdatePackageUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesDeletePackageUseCase(repository: DeliveryRepository): DeletePackageUseCase =
        DeletePackageUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesTrackingNumberUseCase(repository: DeliveryRepository): HandleTrackingNumberUseCase =
        HandleTrackingNumberUseCaseImpl(repository)

    @Provides
    @Singleton
    fun providesPackagesViewModel(
        getAllPackagesUseCase: GetAllPackagesUseCase,
        insertPackageUseCase: InsertPackageUseCase,
        updatePackageUseCase: UpdatePackageUseCase,
        deletePackageUseCase: DeletePackageUseCase,
        trackingNumberUseCase: HandleTrackingNumberUseCase
    ): PackagesViewModel {
        return PackagesViewModel(
            getAllPackagesUseCase,
            insertPackageUseCase,
            updatePackageUseCase,
            deletePackageUseCase,
            trackingNumberUseCase
        )
    }
}