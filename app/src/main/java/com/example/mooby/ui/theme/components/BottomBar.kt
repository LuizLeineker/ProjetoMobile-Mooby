package com.example.mooby.ui.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mooby.navigation.Routes

data class BottomItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun MoobyBottomBar(navController: NavController, modifier: Modifier = Modifier) {

    val items = listOf(
        BottomItem(Routes.MAIN, "Home", Icons.Filled.Home),
        BottomItem(Routes.TRANSACTIONS, "Transações", Icons.Filled.List),
        BottomItem(Routes.OBJECTIVE, "Metas", Icons.Filled.Flag), // <- icone correto
        BottomItem(Routes.PROFILE, "Perfil", Icons.Filled.Person)
    )

    NavigationBar(modifier = modifier) {
        val current = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = current == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Routes.MAIN) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
