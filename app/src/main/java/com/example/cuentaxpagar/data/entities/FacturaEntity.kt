package com.example.cuentaxpagar.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Facturas")
data class FacturaEntity(
    @PrimaryKey(autoGenerate = true) val facturaId: Int = 0,
    val noFactura: String = "",
    val totalFactura: Float = 0.0f,
    val fecha: Long = 0L,
    val fechaVencimiento: Long = 0L,
    val estado: Boolean = false,
    val proveedorId: Int = 0

)
