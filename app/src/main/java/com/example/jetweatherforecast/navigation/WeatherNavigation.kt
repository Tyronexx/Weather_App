package com.example.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherforecast.screens.about.AboutScreen
import com.example.jetweatherforecast.screens.favourites.FavouriteScreen
import com.example.jetweatherforecast.screens.main.MainScreen
import com.example.jetweatherforecast.screens.main.MainViewModel
import com.example.jetweatherforecast.screens.search.SearchScreen
import com.example.jetweatherforecast.screens.settings.SettingsScreen
import com.example.jetweatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ){

        composable(route = WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name        //we do this so we can pass data (city name) from search screen back to mainscreen
        composable(
            route = "$route/{city}",                      //MainScreen should ask for a city
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType         //specify what kind of data we're passing
                }
            )
        ){ navBack ->
//            package that contain pieces of data
            navBack.arguments?.getString("city").let { city ->           //take specific city and pass it to mainScreen

                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    city = city
                )
            }

        }

        composable(route = WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(route = WeatherScreens.AboutScreen.name){
            AboutScreen(navController = navController)
        }

        composable(route = WeatherScreens.SettingsScreen.name){
            SettingsScreen(navController = navController)
        }

        composable(route = WeatherScreens.FavouriteScreen.name){
            FavouriteScreen(navController = navController)
        }

    }

}