package com.gitmad.duofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SpeakerNotesOff
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.ui.DescriptionQuizScreen
import com.gitmad.duofit.ui.FavoritesScreen
import com.gitmad.duofit.ui.HomeScreen
import com.gitmad.duofit.ui.InterestsQuizScreen
import com.gitmad.duofit.ui.LoginScreen
import com.gitmad.duofit.ui.MainGymQuizScreen
import com.gitmad.duofit.ui.MessagesScreen
import com.gitmad.duofit.ui.NameQuizScreen
import com.gitmad.duofit.ui.ProfileQuizScreen
import com.gitmad.duofit.ui.RegistrationScreen
import com.gitmad.duofit.ui.ScheduleQuizScreen
import com.gitmad.duofit.ui.components.NavigationItem
import com.gitmad.duofit.ui.components.drawerSheet
import com.gitmad.duofit.ui.components.fontFamily
import com.gitmad.duofit.ui.theme.DuoFitTheme
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Box(Modifier.windowInsetsPadding(WindowInsets.displayCutout)) {
                DuoFitTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainScaffoldLayout()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffoldLayout(
    mainViewModel: MainViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentScreen by mainViewModel.currentScreen.observeAsState(initial = Screen.Splash)

    val isFeatureScreen = currentScreen is Screen.Home || currentScreen is Screen.Favorites || currentScreen is Screen.Messages

    val showFab = mainViewModel.showFab.observeAsState()
    val user by mainViewModel.user.observeAsState()

    val items = listOf(
        NavigationItem(
            title = "Edit Profile",
            icon = Icons.Filled.Edit,
            onClick = { mainViewModel.navigateTo(Screen.ProfileQuiz) }
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
            onClick = {
                println("hello")
                mainViewModel.navigateTo(Screen.Home)
            }
        ),
        NavigationItem(
            title = "Log Out",
            icon = Icons.Filled.Logout,
            onClick = {
                mainViewModel.navigateTo(Screen.Login)
            }
        ),
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (isFeatureScreen) {
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
                            text = user!!.getDetails().name,
                            style = TextStyle(
                                fontSize = 28.sp,
                                lineHeight = 32.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight(400),
                                color = LocalContentColor.current,

                                letterSpacing = 0.5.sp,
                            ),
                            modifier = Modifier
                                .width(248.dp)
                                .height(36.dp)
                        )
                    }
                    drawerSheet(drawerState = drawerState, scope = scope, null, items, itemsBottom)
                }
            }
        },
        gesturesEnabled = isFeatureScreen
    ) {
        Scaffold(
            topBar = { if (isFeatureScreen) {
                TopAppBar(
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondary,
                        scrolledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    title = { Text(currentScreen.friendlyName) },
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
            }
                     },
            bottomBar = { if (isFeatureScreen) { BottomNavigationBar(mainViewModel = mainViewModel) } },
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
                    is Screen.ProfileQuiz -> ProfileQuizScreen(mainViewModel)
                    is Screen.Login -> LoginScreen(mainViewModel)
                    is Screen.Registration -> RegistrationScreen(mainViewModel)
                    is Screen.Home -> HomeScreen()
                    is Screen.Messages -> MessagesScreen(mainViewModel)
                    is Screen.Favorites -> FavoritesScreen()
                    is Screen.NameQuiz -> NameQuizScreen(mainViewModel)
                    is Screen.InterestsQuiz -> InterestsQuizScreen(mainViewModel)
                    is Screen.ScheduleQuiz -> ScheduleQuizScreen(mainViewModel)
                    is Screen.DescriptionQuiz -> DescriptionQuizScreen(mainViewModel)
                    is Screen.MainGymQuiz -> MainGymQuizScreen(mainViewModel)
                    else -> {}
                }
            }
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
            onClick = { mainViewModel.navigateTo(Screen.Messages) },
            icon = {Icon(painterResource(id = R.drawable.baseline_chat_24), contentDescription = "Messages")},
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