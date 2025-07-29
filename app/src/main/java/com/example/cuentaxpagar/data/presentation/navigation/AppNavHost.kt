import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cuentaxpagar.data.presentation.factura.FacturaListScreen
import com.example.cuentaxpagar.data.presentation.factura.FacturaRegistroScreen
import com.example.cuentaxpagar.data.presentation.proveedor.ProveedorRegistroScreen

sealed class Screen(val route: String) {
    object FacturaListScreen : Screen("factura_list")
    object FacturaRegistroScreen : Screen("factura_registro/{facturaId}") {
        fun createRoute(facturaId: Int) = "factura_registro/$facturaId"
    }
    object ProveedorScreen : Screen("proveedor_screen/{proveedorId}") {
        fun createRoute(proveedorId: Int) = "proveedor_screen/$proveedorId"
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.FacturaListScreen.route
    ) {
        composable(Screen.FacturaListScreen.route) {
            FacturaListScreen(
                irAFacturaDetalle = { facturaId ->
                    navController.navigate(Screen.FacturaRegistroScreen.createRoute(facturaId))
                },
                onAddProveedor = {
                    navController.navigate(Screen.ProveedorScreen.createRoute(0))
                }
            )
        }
        composable(
            route = Screen.FacturaRegistroScreen.route,
            arguments = listOf(navArgument("facturaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val facturaId = backStackEntry.arguments?.getInt("facturaId") ?: 0
            FacturaRegistroScreen(
                viewModel = hiltViewModel(),
                onConsultaFacturas = { navController.navigate(Screen.FacturaListScreen.route) }
            )
        }
        composable(
            route = Screen.ProveedorScreen.route,
            arguments = listOf(navArgument("proveedorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val proveedorId = backStackEntry.arguments?.getInt("proveedorId") ?: 0
            ProveedorRegistroScreen(
                viewModel = hiltViewModel(),  // Usa hiltViewModel para obtener el ViewModel inyectado
                onConsultaProveedores = { navController.navigate(Screen.FacturaListScreen.route) }
            )
        }
    }
}
