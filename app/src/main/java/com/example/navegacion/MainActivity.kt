package com.example.navegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacion.ui.pantallas.characters.CharacterScreen
import com.example.navegacion.ui.theme.NavegacionTheme
import com.example.navegacion.ui.pantallas.detail.DetailScreen
import com.example.navegacion.ui.pantallas.home.HomeScreen

enum class Destinations(
    val route: String, val label: String, val icon: ImageVector, val description: String
) {
    Home(
        route = "home ",
        label = "Home",
        icon = Icons.Filled.Home,
        description = "Pantalla Principal"
    ),
    Detail(
        route = "details",
        label = "Detalle ",
        icon = Icons.Filled.Person,
        description = "Pantalla Detalles "
    ),
    Characters(
        route = "characters",
        label = "Personajes",
        icon = Icons.Filled.Person,
        description = "Personajes"
    )

}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegacionTheme {
                val navController: NavHostController = rememberNavController()
                val starDestination = Destinations.Home
                var selectedDestination by rememberSaveable { mutableIntStateOf(starDestination.ordinal) }
                Scaffold(modifier = Modifier, topBar = {
                    TopAppBar(title = { Text(text = Destinations.entries[selectedDestination].label) })
                }, bottomBar = {
                    NavigationBar {
                        Destinations.entries.forEachIndexed { index, destination ->
                            NavigationBarItem(selected = selectedDestination == index, onClick = {
                                navController.navigate(route = destination.route)
                                selectedDestination = index
                            }, icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.description
                                )
                            }, label = { Text(destination.description) })
                        }
                    }
                }) { contentPadding ->
                    Box(modifier = Modifier.padding(contentPadding)) {
                        AppNavHost(
                            navController,
                            starDestination,
                            modifier = Modifier.padding(contentPadding)
                        )
                    }

                }
            }
        }
    }
}


@Composable
fun AppNavHost(
    navController: NavHostController, startDestinations: Destinations, modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = startDestinations.route) {
        Destinations.entries.forEach { destinations ->
            composable(destinations.route) {
                when (destinations) {
                    Destinations.Home -> HomeScreen()
                    Destinations.Detail -> DetailScreen()
                    Destinations.Characters -> CharacterScreen()
                }
            }
        }
    }
}