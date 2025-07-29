package com.example.cuentaxpagar.data.presentation.proveedor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuentaxpagar.data.entities.ProveedorEntity
import com.example.cuentaxpagar.data.repository.ProveedorRepository
import com.example.cuentaxpagar.presentation.proveedor.ProveedorUiState
import com.example.cuentaxpagar.presentation.proveedor.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProveedorViewModel @Inject constructor(
    private val proveedorRepository: ProveedorRepository
) : ViewModel() {

    var uiState = MutableStateFlow(ProveedorUiState())
        private set

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage

    init {
        getProveedores()
    }

    fun onProveedorNameChanged(proveedorName: String) {
        uiState.update {
            it.copy(proveedorName = proveedorName)
        }
    }

    fun onContactoVendedorChanged(contactoVendedor: String) {
        uiState.update {
            it.copy(contactoVendedor = contactoVendedor)
        }
    }

    fun onBalanceChanged(balance: String) {
        val balanceFloat = balance.toFloatOrNull() ?: 0f
        uiState.update {
            it.copy(balance = balanceFloat)
        }
    }

    fun onDescuentoChanged(descuento: String) {
        val descuentoFloat = descuento.toFloatOrNull() ?: 0f
        uiState.update {
            it.copy(descuento = descuentoFloat)
        }
    }

    fun onSetProveedor(proveedorId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val proveedor = proveedorRepository.getProveedorById(proveedorId)
                proveedor?.let {
                    uiState.update { currentState ->
                        currentState.copy(
                            proveedorId = it.proveedorId,
                            proveedorName = it.proveedorName,
                            contactoVendedor = it.contactoVendedor,
                            balance = it.balance,
                            descuento = it.descuento
                        )
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getProveedores() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                proveedorRepository.getProveedoresLocal()
                    .collect { proveedores ->
                        uiState.update {
                            it.copy(proveedores = proveedores)
                        }
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveProveedor(proveedorName: String, contactoVendedor: String, balance: Float, descuento: Float) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                proveedorRepository.saveProveedor(
                    ProveedorEntity(
                        proveedorName = proveedorName,
                        contactoVendedor = contactoVendedor,
                        balance = balance,
                        descuento = descuento
                    )
                )
                getProveedores()
                newProveedor()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun newProveedor() {
        uiState.update {
            ProveedorUiState()
        }
    }

    fun deleteProveedor() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                proveedorRepository.deleteProveedor(uiState.value.toEntity())
                getProveedores()
                newProveedor()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }
}
