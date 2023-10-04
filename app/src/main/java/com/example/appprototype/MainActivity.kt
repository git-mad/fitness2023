package com.example.appprototype

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appprototype.ui.theme.AppPrototypeTheme
import androidx.compose.runtime.mutableStateOf;
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppPrototypeTheme {
        Greeting("Android")
    }
}
@Composable
fun NavHostContainer(
    navController: NavHostController = rememberNavController(),
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        //modifier = Modifier.padding{padding}
    ){
        composable("Home"){
            homePage(padding)
        }
        composable("Favorites"){
            favoritesPage(padding)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    //var presses by remember { mutableIntStateOf(0) }
    //val navController = NavController()
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    var pageName by remember { mutableStateOf("Home") }
    val navController = rememberNavController()
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
        homePage(innerPadding)
    }
}

@Composable
fun homePage(innerPadding : PaddingValues){
    LazyColumn(modifier = Modifier
        .padding(innerPadding)
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ){
        item (){
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
            makeCard()
        }
    }
}

@Composable
fun favoritesPage(innerPadding: PaddingValues){
    Text("Hello World!")
}

@Preview(showBackground = true)
@Composable
fun ScaffoldPreview(){
    AppPrototypeTheme {
        ScaffoldExample()
    }
}

@Composable
fun makeCard(){
    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp
    ),
        modifier = Modifier
            .size(LocalConfiguration.current.screenWidthDp.dp - 20.dp, 160.dp)
            .clickable {}
            .padding(vertical = 8.dp)){
        Row(modifier = Modifier
            .padding(start = 10.dp,
                top = 20.dp,
                bottom = 20.dp,
                end = 10.dp
            )
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .align(CenterVertically)
                    .clip(CircleShape)
            )
            Text(
                "Hello World",
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterVertically),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }

    }
}