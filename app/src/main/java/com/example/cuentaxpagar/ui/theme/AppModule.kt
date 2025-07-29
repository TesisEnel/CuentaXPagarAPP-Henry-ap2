package com.example.cuentaxpagar.di

import android.content.Context
import androidx.room.Room
import com.example.cuentaxpagar.data.database.FacturaDb
import com.example.cuentaxpagar.data.local.dao.FacturaDao
import com.example.cuentaxpagar.data.local.dao.ProveedorDao
import com.example.cuentaxpagar.data.remote.FacturaApi
import com.example.cuentaxpagar.data.remote.ProveedorApi
import com.example.cuentaxpagar.data.repository.ProveedorRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFacturaDatabase(@ApplicationContext appContext: Context): FacturaDb {
        return Room.databaseBuilder(
            appContext,
            FacturaDb::class.java,
            "factura.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFacturaDao(db: FacturaDb): FacturaDao = db.facturaDao()

    @Provides
    fun provideProveedorDao(db: FacturaDb): ProveedorDao = db.proveedorDao()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cuenta-ftabg3d0gdd0dbhx.eastus2-01.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideProveedorApi(retrofit: Retrofit): ProveedorApi {
        return retrofit.create(ProveedorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFacturaApi(retrofit: Retrofit): FacturaApi {
        return retrofit.create(FacturaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProveedorRepository(
        proveedorDao: ProveedorDao
    ): ProveedorRepository {
        return ProveedorRepository(proveedorDao)
    }

}
