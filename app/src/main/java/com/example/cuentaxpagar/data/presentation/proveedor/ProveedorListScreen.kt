package com.example.cuentaxpagar.data.presentation.proveedor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cuentaxpagar.data.entities.ProveedorEntity
import com.example.cuentaxpagar.presentation.proveedor.ProveedorUiState

@Composable
fun ProveedorListScreen(
    viewModel: ProveedorViewModel = hiltViewModel(),
    onVerProveedor: (ProveedorEntity) -> Unit,
    onAddProveedor: () -> Unit,
    irADashboard: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProveedorListBody(
        proveedores = uiState.proveedores,
        onVerProveedor = onVerProveedor,
        onAddProveedor = onAddProveedor,
        onGetProveedores = { viewModel.getProveedores() },
        uistate = uiState,
        irADashboard = { irADashboard() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProveedorListBody(
    proveedores: List<ProveedorEntity>,
    onVerProveedor: (ProveedorEntity) -> Unit,
    onAddProveedor: () -> Unit,
    onGetProveedores: () -> Unit,
    uistate: ProveedorUiState,
    irADashboard: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Lista de Proveedores")
                        TextButton(onClick = { onGetProveedores() }) {
                            Text(text = "Get Proveedores")
                        }
                        if (uistate.isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = irADashboard) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atras"
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProveedor) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Proveedor")
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Id",
                    modifier = Modifier.weight(0.20f)
                )

                Text(
                    text = "Nombre",
                    modifier = Modifier.weight(0.40f)
                )
            }

            if (uistate.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(proveedores) { item ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onVerProveedor(item)
                        }
                        .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            text = item.proveedorId.toString(),
                            modifier = Modifier.weight(0.20f)
                        )
                        Text(
                            text = item.proveedorName ?: "",
                            modifier = Modifier.weight(0.40f)
                        )
                    }
                }
            }
        }
    }
}
