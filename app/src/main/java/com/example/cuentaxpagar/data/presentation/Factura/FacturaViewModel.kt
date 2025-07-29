package com.example.cuentaxpagar.data.presentation.factura

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cuentaxpagar.data.entities.FacturaEntity
import com.example.cuentaxpagar.data.entities.ProveedorEntity
import com.example.cuentaxpagar.data.repository.FacturaRepositoryImpl
import com.example.cuentaxpagar.data.repository.ProveedorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacturaViewModel @Inject constructor(
    private val facturaRepository: FacturaRepositoryImpl,
    private val proveedorRepository: ProveedorRepository
) : ViewModel() {

    private val _facturas: LiveData<List<FacturaEntity>> = facturaRepository.getFacturasLocal().asLiveData()
    private val _proveedores: LiveData<List<ProveedorEntity>> = proveedorRepository.getProveedoresLocal().asLiveData()
    private val _searchQuery = MutableLiveData<String>("")

    val searchQuery: LiveData<String> get() = _searchQuery
    val proveedores: LiveData<List<ProveedorEntity>> get() = _proveedores
    val filteredFacturas = MediatorLiveData<List<FacturaEntity>>()

    init {
        filteredFacturas.addSource(_facturas) { filterFacturas() }
        filteredFacturas.addSource(_proveedores) { filterFacturas() }
        filteredFacturas.addSource(_searchQuery) { filterFacturas() }
    }

    private fun filterFacturas() {
        val query = _searchQuery.value ?: ""
        val facturas = _facturas.value ?: emptyList()
        val proveedores = _proveedores.value ?: emptyList()

        filteredFacturas.value = if (query.isBlank()) {
            facturas
        } else {
            val proveedorId = proveedores.firstOrNull {
                it.proveedorName.contains(query, ignoreCase = true)
            }?.proveedorId

            facturas.filter { it.proveedorId == proveedorId }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private val _factura = MutableLiveData<FacturaEntity?>()
    val factura: LiveData<FacturaEntity?> get() = _factura

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun obtenerFacturaPorId(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _factura.value = facturaRepository.getFacturaLocal(id)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun guardarFactura(factura: FacturaEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                facturaRepository.saveFactura(factura)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun eliminarFactura(factura: FacturaEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                facturaRepository.deleteFacturaLocal(factura)
                _errorMessage.value = null
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
