package com.example.cuentaxpagar.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Shapes as Shapes1

private val DarkColorPalette = darkColorScheme(
    primary = Purple500,
    primaryContainer = Purple700,
    secondary = Teal200,
    background = BackgroundColor,
    surface = CardColor,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = TextColor,
    onSurface = TextColor,
)

@Composable
fun CuentaXPagarTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = Typography,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCuentaXPagarTheme() {
    CuentaXPagarTheme {
        // Add content to preview
    }
}
