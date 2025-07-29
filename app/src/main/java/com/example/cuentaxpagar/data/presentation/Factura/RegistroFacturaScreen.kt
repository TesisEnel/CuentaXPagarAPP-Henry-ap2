package com.example.cuentaxpagar.data.presentation.factura

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cuentaxpagar.data.entities.FacturaEntity
import com.example.cuentaxpagar.data.entities.ProveedorEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturaRegistroScreen(
    viewModel: FacturaViewModel = hiltViewModel(),
    onConsultaFacturas: () -> Unit
) {
    val proveedores by viewModel.proveedores.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)
    var proveedorSeleccionado by remember { mutableStateOf<ProveedorEntity?>(null) }
    var noFactura by remember { mutableStateOf("") }
    var totalFactura by remember { mutableStateOf("") }
    var fechaVencimiento by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            viewModel.clearErrorMessage()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Facturas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (proveedorSeleccionado != null && noFactura.isNotBlank() && totalFactura.isNotBlank() && fechaVencimiento.isNotBlank()) {
                    viewModel.guardarFactura(
                        FacturaEntity(
                            noFactura = noFactura,
                            totalFactura = totalFactura.toFloat(),
                            fecha = System.currentTimeMillis(),
                            fechaVencimiento = fechaVencimiento.toLong(),
                            proveedorId = proveedorSeleccionado!!.proveedorId
                        )
                    )
                    Toast.makeText(context, "Factura guardada correctamente", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(Icons.Default.Save, contentDescription = "Guardar Factura")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Selección de Proveedor
                    Box {
                        OutlinedTextField(
                            value = proveedorSeleccionado?.proveedorName ?: "Seleccione un proveedor",
                            onValueChange = {},
                            label = { Text("Proveedor") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { expanded = !expanded },
                            readOnly = true
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            proveedores.forEach { proveedor ->
                                DropdownMenuItem(
                                    onClick = {
                                        proveedorSeleccionado = proveedor
                                        expanded = false
                                    }
                                ) {
                                    Text(proveedor.proveedorName ?: "Sin Nombre")
                                }
                            }
                        }
                    }

                    // No. Factura
                    OutlinedTextField(
                        value = noFactura,
                        onValueChange = { noFactura = it },
                        label = { Text("No. Factura") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    // Total Factura
                    OutlinedTextField(
                        value = totalFactura,
                        onValueChange = { totalFactura = it },
                        label = { Text("Total Factura") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    // Fecha de Vencimiento
                    OutlinedTextField(
                        value = fechaVencimiento,
                        onValueChange = { fechaVencimiento = it },
                        label = { Text("Fecha de Vencimiento ") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para ir a la consulta de facturas
                    Button(
                        onClick = onConsultaFacturas,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Consultar Facturas")
                    }
                }
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {
    TODO("Not yet implemented")
}
