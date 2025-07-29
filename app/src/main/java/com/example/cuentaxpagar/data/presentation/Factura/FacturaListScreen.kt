package com.example.cuentaxpagar.data.presentation.factura

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cuentaxpagar.data.entities.FacturaEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturaListScreen(
    viewModel: FacturaViewModel = hiltViewModel(),
    irAFacturaDetalle: (Int) -> Unit,
    onAddProveedor: () -> Unit
) {
    val facturas by viewModel.filteredFacturas.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    val context = LocalContext.current

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.clearErrorMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Facturas") })
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                FloatingActionButton(onClick = { irAFacturaDetalle(0) }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Factura")
                }
                FloatingActionButton(onClick = onAddProveedor) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Proveedor")
                }
            }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (facturas.isEmpty()) {
                    Text(
                        text = "No hay facturas disponibles",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(facturas) { factura ->
                            FacturaListItem(factura, irAFacturaDetalle)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FacturaListItem(
    factura: FacturaEntity,
    onFacturaClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onFacturaClick(factura.facturaId) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "No. Factura: ${factura.noFactura}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Total: ${factura.totalFactura}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Fecha: ${factura.fecha.formatDate()}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Fecha Vencimiento: ${factura.fechaVencimiento.formatDate()}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Estado: ${if (factura.estado) "Pagada" else "Pendiente"}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Proveedor ID: ${factura.proveedorId}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

fun Long.formatDate(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return sdf.format(this)
}
