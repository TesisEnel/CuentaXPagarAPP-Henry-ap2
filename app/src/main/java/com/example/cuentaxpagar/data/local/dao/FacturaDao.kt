package com.example.cuentaxpagar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cuentaxpagar.data.entities.FacturaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FacturaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(factura: FacturaEntity)

    @Query(
        """
        SELECT * 
        FROM Facturas
        WHERE facturaId = :id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): FacturaEntity?

    @Delete
    suspend fun delete(factura: FacturaEntity)

    @Query("SELECT * FROM Facturas")
    fun getAll(): Flow<List<FacturaEntity>>

    @Query("SELECT * FROM Facturas")
    suspend fun getAllSync(): List<FacturaEntity>
}
