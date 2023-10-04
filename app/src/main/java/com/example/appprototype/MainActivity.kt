package com.example.appprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appprototype.ui.theme.AppPrototypeTheme
import androidx.compose.runtime.mutableStateOf;
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.appprototype.ui.homePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppPrototypeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldExample();
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(
    navController: NavHostController = rememberNavController()
) {
    //var presses by remember { mutableIntStateOf(0) }
    //val navController = NavController()
    var selectedItem by remember { mutableStateOf(0) }
    var pageName by remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
                    scrolledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Text(pageName)
                },
                navigationIcon = {
                    IconButton(onClick = {/**/}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val home = "Home"
                val favorites = "Favorites"
                val messages = "Messages"
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0
                              pageName = home
                              navController.navigate(home)
                              },
                    icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                    label = {Text(home)}
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1
                              pageName = favorites
                        navController.navigate(favorites)},
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = {Text(favorites)}
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2
                              pageName = messages},
                    icon = { Icon(Icons.Filled.Email, contentDescription = null) },
                    label = {Text(messages)}
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "Home",
            modifier = Modifier.padding(innerPadding),
        ){
            composable("Home"){
                homePage()
            }
            composable("Favorites"){
                favoritesPage()
            }
        }
    }
}



@Composable
fun favoritesPage(){
    Text("Hello World!")
}

@Preview(showBackground = true)
@Composable
fun ScaffoldPreview(){
    AppPrototypeTheme {
        ScaffoldExample()
    }
}

