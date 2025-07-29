package com.example.cuentaxpagar.data.entities



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Proveedors")
data class ProveedorEntity(
    @PrimaryKey(autoGenerate = true) val proveedorId: Int = 0,
    val proveedorName: String,
    val contactoVendedor: String,
    val balance: Float,
    val descuento: Float
)



