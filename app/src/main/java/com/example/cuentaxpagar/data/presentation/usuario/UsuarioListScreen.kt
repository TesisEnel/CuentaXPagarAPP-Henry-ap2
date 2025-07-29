package com.example.cuentaxpagar.data.presentation.usuario

/*
@Composable
fun UsuarioListScreen(
    viewModel: UsuarioViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.errorMessage != null) {
            Text(text = "Error: ${uiState.errorMessage}")
        } else {
            LazyColumn {
                items(uiState.usuarios) { usuario ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "ID: ${usuario.usuarioId}")
                        Text(text = "Correo: ${usuario.correo}")
                        Text(text = "Contraseña: ${usuario.contrasena}")
                    }
                }
            }
        }
    }
}
*/