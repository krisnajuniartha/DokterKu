package com.example.dokterku

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dokterku.ui.application.detail.DetailScreen
import com.example.dokterku.ui.application.home.HomeScreen
import com.example.dokterku.ui.application.profile.ProfileScreen
import com.example.dokterku.ui.navigation.NavigationItem
import com.example.dokterku.ui.navigation.Screen

@Composable
fun DokterKu(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailDokter.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { userId -> navController.navigate(Screen.DetailDokter.createRoute(userId))}
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    name = "Krisna Juniartha",
                    headline = "gedekrisna22@gmail.com",
                    photoUrl = "https://avatars.githubusercontent.com/u/90083755?v=4",
                    about = "Saya adalah siswa Bangkit yang sangat suka menonton Anime, saya berasal dari learning path MD, semoga saya bisa mendapatkan banyak ilmu disini",
                )
            }
            composable(route = Screen.DetailDokter.route,
                arguments = listOf(navArgument("userId") {type = NavType.StringType}),
            ){
                val userId = it.arguments?.getString("userId") ?: "1"
                DetailScreen(
                    id = userId,
                )
            }

        }
    }
}


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) }
            )
        }
    }
}