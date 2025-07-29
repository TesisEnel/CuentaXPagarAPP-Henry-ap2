package com.example.cuentaxpagar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cuentaxpagar.data.entities.FacturaEntity
import com.example.cuentaxpagar.data.entities.ProveedorEntity
import com.example.cuentaxpagar.data.entities.UsuarioEntity
import com.example.cuentaxpagar.data.local.dao.FacturaDao
import com.example.cuentaxpagar.data.local.dao.ProveedorDao
import com.example.cuentaxpagar.data.local.dao.UsuarioDao

@Database(
    entities = [
        FacturaEntity::class,
        ProveedorEntity::class,
        UsuarioEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class FacturaDb : RoomDatabase() {
    abstract fun facturaDao(): FacturaDao
    abstract fun proveedorDao(): ProveedorDao
    abstract fun usuarioDao(): UsuarioDao
}
