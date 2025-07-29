package com.example.cuentaxpagar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cuentaxpagar.data.entities.ProveedorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProveedorDao {
    @Insert
    suspend fun insertProveedor(proveedor: ProveedorEntity)

    @Query("SELECT * FROM Proveedors")
    fun getAllProveedors(): Flow<List<ProveedorEntity>>  // Cambiado para devolver Flow

    @Query("SELECT * FROM Proveedors WHERE proveedorId = :id LIMIT 1")
    suspend fun getProveedorById(id: Int): ProveedorEntity?

    @Query("DELETE FROM Proveedors WHERE proveedorId = :id")
    suspend fun deleteProveedor(id: Int)
}
