package com.gitmad.duofit.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun drawerSheet(drawerState: DrawerState, scope: CoroutineScope, items: List<NavigationItem>? = null, itemsTop: List<NavigationItem>? = null, itemsBottom: List<NavigationItem>? = null) {
    if (items != null) {
        items.forEachIndexed { index, navigationItem ->
            navigationItem(navigationItem,drawerState,scope)
        }
    } else if (itemsTop != null && itemsBottom != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column (modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
            ){
                itemsTop.forEachIndexed { index, navigationItem ->
                    navigationItem(navigationItem,drawerState,scope)
                }
            }
            Column (modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 10.dp)
            ){
                itemsBottom.forEachIndexed { index, navigationItem ->
                    navigationItem(navigationItem,drawerState,scope)
                }
            }
        }
    } else {
        throw NullPointerException("No proper list configuration provided.")
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun navigationItem(navigationItem: NavigationItem, drawerState: DrawerState, scope: CoroutineScope) {
    NavigationDrawerItem(
        label = { Text(text = navigationItem.title) },
        selected = false,
        onClick = {
            navigationItem.onClick
            scope.launch {
                drawerState.close()
            }
        },
        icon = {
            Icon(
                imageVector = navigationItem.icon,
                contentDescription = navigationItem.title
            )
        },
        modifier = Modifier
            .padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}