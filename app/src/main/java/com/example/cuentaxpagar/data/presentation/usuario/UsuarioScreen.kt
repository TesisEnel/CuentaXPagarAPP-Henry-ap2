package com.example.cuentaxpagar.data.presentation.usuario
/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cuentaxpagar.ui.theme.CuentaXPagarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsuarioScreen(
    viewModel: UsuarioViewModel = hiltViewModel(),
    goToUsuarioList: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    UsuarioBody(
        uiState = uiState,
        onSaveUsuario = {
            viewModel.saveUsuario()
        },
        onDeleteUsuario = { usuarioId ->
            viewModel.deleteUsuario(usuarioId)
        },
        goToUsuarioList = goToUsuarioList,
        onNewUsuario = {
            viewModel.newUsuario()
        },
        onCorreoChanged = viewModel::onCorreoChanged,
        onContrasenaChanged = viewModel::onContrasenaChanged,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UsuarioBody(
    uiState: UsuarioUIState,
    onSaveUsuario: () -> Unit,
    goToUsuarioList: () -> Unit,
    onDeleteUsuario: (Int) -> Unit = {},
    onCorreoChanged: (String) -> Unit,
    onNewUsuario: () -> Unit,
    onContrasenaChanged: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Usuario")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp)
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        label = { Text(text = "Correo") },
                        value = uiState.correo ?: "",
                        onValueChange = onCorreoChanged,
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.correoError != null
                    )
                    if (uiState.correoError != null) {
                        Text(
                            text = uiState.correoError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        label = { Text(text = "Contraseña") },
                        value = uiState.contrasena ?: "",
                        onValueChange = onContrasenaChanged,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        isError = uiState.contrasenaError != null
                    )
                    if (uiState.contrasenaError != null) {
                        Text(
                            text = uiState.contrasenaError,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.padding(2.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedButton(onClick = onNewUsuario) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "new button"
                            )
                            Text(text = "Nuevo")
                        }
                        OutlinedButton(
                            onClick = {
                                onSaveUsuario()
                                goToUsuarioList()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "save button"
                            )
                            Text(text = "Guardar")
                        }
                        OutlinedButton(
                            onClick = {
                                uiState.usuarios.firstOrNull()?.let { usuario ->
                                    onDeleteUsuario(usuario.usuarioId)
                                }
                                goToUsuarioList()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete button"
                            )
                            Text(text = "Borrar")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UsuarioPreview() {
    CuentaXPagarTheme {
        UsuarioBody(
            uiState = UsuarioUIState(),
            onSaveUsuario = {},
            goToUsuarioList = {},
            onDeleteUsuario = {},
            onCorreoChanged = {},
            onNewUsuario = {},
            onContrasenaChanged = {}
        )
    }
}
*/