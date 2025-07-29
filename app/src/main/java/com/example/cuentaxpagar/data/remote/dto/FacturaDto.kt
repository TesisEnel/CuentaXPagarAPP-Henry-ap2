package com.example.cuentaxpagar.data.remote.dto
data class FacturaDto(
    val facturaId: Int,
    val noFactura: String,
    val totalFactura: Float,
    val fecha: String,
    val fechaVencimiento: String,
    val estado: Boolean,
    val proveedorId: Int,
    val proveedor: ProveedorDto? // Descomentar si se necesita incluir información del proveedor
)
