package com.example.cuentaxpagar.data.repository

import com.example.cuentaxpagar.data.entities.ProveedorEntity
import com.example.cuentaxpagar.data.local.dao.ProveedorDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProveedorRepository @Inject constructor(
    private val proveedorDao: ProveedorDao
) {

    fun getProveedoresLocal(): Flow<List<ProveedorEntity>> {
        return proveedorDao.getAllProveedors()  //
    }

    suspend fun saveProveedor(proveedor: ProveedorEntity) {
        proveedorDao.insertProveedor(proveedor)
    }

    suspend fun deleteProveedor(proveedor: ProveedorEntity) {
        proveedorDao.deleteProveedor(proveedor.proveedorId)
    }

    suspend fun getProveedorById(id: Int): ProveedorEntity? {
        return proveedorDao.getProveedorById(id)
    }
}
