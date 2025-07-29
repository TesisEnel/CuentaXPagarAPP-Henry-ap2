package com.example.cuentaxpagar.presentation.proveedor

import com.example.cuentaxpagar.data.entities.ProveedorEntity

data class ProveedorUiState(
    val proveedorId: Int? = null,
    var proveedorName: String? = "",
    var contactoVendedor: String? = "",
    var balance: Float = 0.0f,
    var descuento: Float = 0.0f,
    var proveedorNameError: String? = null,
    var contactoVendedorError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val proveedores: List<ProveedorEntity> = emptyList(),
)

fun ProveedorUiState.toEntity(): ProveedorEntity {
    return ProveedorEntity(
        proveedorId = proveedorId ?: 0,
        proveedorName = proveedorName ?: "",
        contactoVendedor = contactoVendedor ?: "",
        balance = balance,
        descuento = descuento
    )
}
