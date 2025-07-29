package com.example.cuentaxpagar.data.presentation.proveedor


import android.widget.Toast
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProveedorRegistroScreen(
    viewModel: ProveedorViewModel = hiltViewModel(),
    onConsultaProveedores: () -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    var proveedorName by remember { mutableStateOf("") }
    var contactoVendedor by remember { mutableStateOf("") }
    var balance by remember { mutableStateOf("") }
    var descuento by remember { mutableStateOf("") }

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
                title = { Text("Registro de Proveedores") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (proveedorName.isNotBlank() && contactoVendedor.isNotBlank() && balance.isNotBlank() && descuento.isNotBlank()) {
                    viewModel.saveProveedor(
                        proveedorName,
                        contactoVendedor,
                        balance.toFloat(),
                        descuento.toFloat()
                    )
                    Toast.makeText(context, "Proveedor guardado correctamente", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
                }
            }) {
                Icon(Icons.Default.Save, contentDescription = "Guardar Proveedor")
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
                    // Nombre del Proveedor
                    OutlinedTextField(
                        value = proveedorName,
                        onValueChange = { proveedorName = it },
                        label = { Text("Nombre del Proveedor") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Contacto del Vendedor
                    OutlinedTextField(
                        value = contactoVendedor,
                        onValueChange = { contactoVendedor = it },
                        label = { Text("Contacto del Vendedor") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Balance
                    OutlinedTextField(
                        value = balance,
                        onValueChange = { balance = it },
                        label = { Text("Balance") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    // Descuento
                    OutlinedTextField(
                        value = descuento,
                        onValueChange = { descuento = it },
                        label = { Text("Descuento") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para ir a la consulta de proveedores
                    Button(
                        onClick = onConsultaProveedores,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Consultar Proveedores")
                    }
                }
            }
        }
    }
}
