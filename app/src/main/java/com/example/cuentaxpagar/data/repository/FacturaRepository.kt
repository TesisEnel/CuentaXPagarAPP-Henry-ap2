package com.example.cuentaxpagar.data.repository

import android.util.Log
import com.example.cuentaxpagar.data.entities.FacturaEntity
import com.example.cuentaxpagar.data.local.dao.FacturaDao
import com.example.cuentaxpagar.data.remote.FacturaApi
import com.example.cuentaxpagar.data.remote.dto.FacturaDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FacturaRepositoryImpl @Inject constructor(
    private val facturasAPI: FacturaApi,
    private val facturaDao: FacturaDao
) {
    suspend fun saveFactura(factura: FacturaEntity) =
        facturaDao.save(factura)

    fun getFacturasLocal(): Flow<List<FacturaEntity>> =
        facturaDao.getAll()

    suspend fun deleteFacturaLocal(factura: FacturaEntity) =
        facturaDao.delete(factura)

    suspend fun getFacturaLocal(facturaId: Int): FacturaEntity? =
        facturaDao.find(facturaId)

    suspend fun getFacturasLocalSync(): List<FacturaEntity> =
        facturaDao.getAllSync()

    suspend fun getFactura(id: Int): FacturaDto? {
        return try {
            facturasAPI.getFactura(id)
        } catch (e: Exception) {
            Log.e("Repositorio", "getFactura: ${e.message}")
            null
        }
    }

    fun getFacturas(): Flow<Resource<List<FacturaDto>>> = flow {
        emit(Resource.Loading())
        try {
            val facturas = facturasAPI.getFacturas()
            emit(Resource.Success(facturas))
        } catch (e: Exception) {
            Log.e("Repositorio", "getFacturas: ${e.message}")
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }

    suspend fun putFactura(factura: FacturaDto) {
        try {
            facturasAPI.putFactura(factura.facturaId, factura)
        } catch (e: Exception) {
            Log.e("Repositorio", "putFactura: ${e.message}")
        }
    }

    suspend fun postFactura(factura: FacturaDto) {
        try {
            facturasAPI.postFactura(factura)
        } catch (e: Exception) {
            Log.e("Repositorio", "postFactura: ${e.message}")
        }
    }

    suspend fun deleteFactura(factura: FacturaDto) {
        try {
            facturasAPI.deleteFactura(factura.facturaId)
        } catch (e: Exception) {
            Log.e("Repositorio", "deleteFactura: ${e.message}")
        }
    }
}

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
