package com.example.cuentaxpagar.data.remote.dto

data class ProveedorDto(
    val proveedorId: Int,
    val proveedorName: String,
    val contactoVendedor: String,
    val balance: Float,
    val descuento: Float
)