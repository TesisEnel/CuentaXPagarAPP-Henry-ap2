package com.example.cuentaxpagar.data.presentation.usuario
/*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cuentaxpagar.data.remote.dto.UsuarioDto
import com.example.cuentaxpagar.data.repository.Resource
import com.example.cuentaxpagar.data.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UsuarioUIState())
    val uiState: StateFlow<UsuarioUIState> = _uiState.asStateFlow()

    init {
        getUsuario()
    }

    private fun getUsuario() {
        viewModelScope.launch {
            usuarioRepository.getUsuario().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            usuarios = result.data ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun saveUsuario() {
        viewModelScope.launch {
            val usuario = UsuarioDto(
                usuarioId = _uiState.value.usuarioId ?: 0,
                correo = _uiState.value.correo.orEmpty(),
                contrasena = _uiState.value.contrasena.orEmpty()
            )
            usuarioRepository.createUsuario(usuario).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        getUsuario()
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun deleteUsuario(usuarioId: Int) {
        viewModelScope.launch {
            usuarioRepository.deleteUsuario(usuarioId).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        getUsuario()
                    }
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun newUsuario() {
        _uiState.value = UsuarioUIState()
    }

    fun onCorreoChanged(correo: String) {
        _uiState.value = _uiState.value.copy(correo = correo)
    }

    fun onContrasenaChanged(contrasena: String) {
        _uiState.value = _uiState.value.copy(contrasena = contrasena)
    }

    fun onUsuarioIdChanged(usuarioId: Int) {
        _uiState.value = _uiState.value.copy(usuarioId = usuarioId)
    }
}

data class UsuarioUIState(
    val usuarios: List<UsuarioDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val usuarioId: Int? = null,
    val correo: String? = null,
    val contrasena: String? = null,
    val correoError: String? = null,
    val contrasenaError: String? = null
)
*/