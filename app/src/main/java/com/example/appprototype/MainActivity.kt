package com.example.appprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appprototype.ui.HomeScreen
import com.example.appprototype.ui.MessagesScreen
import com.example.appprototype.ui.components.NavigationItem
import com.example.appprototype.ui.components.drawerSheet
import com.example.appprototype.ui.components.fontFamily
import com.example.appprototype.ui.profileScreen
import com.example.appprototype.ui.theme.AppPrototypeTheme
import com.example.appprototype.viewmodels.MainViewModel
import com.example.appprototype.viewmodels.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Box(Modifier.windowInsetsPadding(WindowInsets.displayCutout)) {
                AppPrototypeTheme {
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
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentScreen by mainViewModel.currentScreen.observeAsState(initial = Screen.Home)
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showProfileSheet by remember { mutableStateOf(false) }
    val showFab = mainViewModel.showFab.observeAsState()

    val items = listOf(
        NavigationItem(
            title = "Edit Profile",
            icon = Icons.Filled.Edit,
            onClick = {}
        ),
        NavigationItem(
            title = "Communities",
            icon = ImageVector.vectorResource(R.drawable.account_group),
            onClick = {}
        ),
    )
    val itemsBottom = listOf(
        NavigationItem(
            title = "Settings",
            icon = Icons.Filled.Settings,
            onClick = {}
        )
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    NavigationDrawerItem(
                        label = {
                            Image(Icons.Filled.ArrowBack, contentDescription = "Go Back")
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        modifier = Modifier
                            .width(60.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.default_profile_icon),
                            contentDescription = "User Profile",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(128.dp)
                                .height(128.dp)
                                .clip(CircleShape)
                        )
                    }
                    Text(
                        text = "Anne Joe",
                        style = TextStyle(
                            fontSize = 28.sp,
                            lineHeight = 32.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF191C1D),

                            letterSpacing = 0.5.sp,
                        ),
                        modifier = Modifier
                            .width(248.dp)
                            .height(36.dp)
                    )
                }
                drawerSheet(drawerState = drawerState, scope = scope, null, items, itemsBottom)
            }
        },
        gesturesEnabled = true
    ) {
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
                    title = { Text(text = currentScreen.friendlyName ) },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = null)
                        }
                    }
                )
            },
            bottomBar = { BottomNavigationBar(mainViewModel = mainViewModel) },
            floatingActionButton = {
                if (showFab.value!!){
                    FloatingActionButton(onClick = { /*TODO*/ }) {
                        Icon(ImageVector.vectorResource(R.drawable.filter_list), contentDescription = "Filter List")
                    }
                }
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier.padding(innerPadding),
            ) {
                when (currentScreen) {
                    is Screen.Home -> HomeScreen {
                        showProfileSheet = true
                    }

                    is Screen.Messages -> MessagesScreen()

                    else -> {}
                }
            }
            if (showProfileSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showProfileSheet = false },
                    sheetState = sheetState
                ) {
                    profileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(onBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = { Text("Profile") }
    )
    profileScreen()
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

@Composable
fun NavController.currentRoute(): String? {
    val navBackStackEntry by this.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

fun NavController.navigateSafe(route: String) {
    if (currentDestination?.route != route) {
        navigate(route) {
            popUpTo(graph.startDestinationId){
                saveState = true
            }
            restoreState = true
        }
    }
}

@Composable
fun BottomNavigationBar(mainViewModel: MainViewModel) {
    val currentScreen by mainViewModel.currentScreen.observeAsState(initial = Screen.Home)

    NavigationBar {
        NavigationBarItem(
            selected = currentScreen is Screen.Home,
            onClick = { mainViewModel.navigateTo(Screen.Home) },
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentScreen is Screen.Messages,
            onClick = {mainViewModel.navigateTo(Screen.Messages)},
            icon = {Icon(painterResource(id = R.drawable.chat), contentDescription = "Messages")},
            label = { Text("Messages") }
        )
        NavigationBarItem(
            selected = currentScreen is Screen.Favorites,
            onClick = { mainViewModel.navigateTo(Screen.Favorites) },
            icon = { Icon(painterResource(id = R.drawable.star_box), contentDescription = "Favorites") },
            label = { Text("Favorites") }
        )
    }
}
