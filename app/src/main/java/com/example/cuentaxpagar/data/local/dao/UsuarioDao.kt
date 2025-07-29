package com.example.cuentaxpagar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.cuentaxpagar.data.entities.UsuarioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Upsert
    suspend fun saveUsuario(usuario: UsuarioEntity)

    @Query(
        """
            SELECT *
            FROM Usuarios
            WHERE usuarioId=:id
            LIMIT 1
        """
    )
    suspend fun findUsuario(id: Int) : UsuarioEntity?

    @Delete
    suspend fun deleteUsuario(usuario: UsuarioEntity)

    @Query("SELECT * FROM Usuarios")
    fun getAllUsuarios(): Flow<List<UsuarioEntity>>

    @Query("SELECT * FROM Usuarios WHERE correo = :correo LIMIT 1")
    suspend fun getUsuarioByEmail(correo: String): UsuarioEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsuarios(usuarios: List<UsuarioEntity>)


}