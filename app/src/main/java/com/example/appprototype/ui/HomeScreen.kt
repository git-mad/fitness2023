package com.example.appprototype.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appprototype.models.Profile
import com.example.appprototype.ui.components.profileCard
import com.example.appprototype.viewmodels.HomeViewModel
import com.example.appprototype.viewmodels.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val profileList by viewModel.fetchedProfileList.observeAsState(initial = mutableListOf())
    val showProfileSheet by viewModel.showProfileSheet.observeAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    val scope = rememberCoroutineScope()
    val listUpToDate = viewModel.listUpToDate.observe(LocalLifecycleOwner.current) {
        if (it == false) {
            viewModel.fetchList()
        }
    }

    LazyColumn(modifier = Modifier,
        //verticalArrangement = Arrangement.spacedBy(100.dp)
    ){
        items(profileList) {profile ->
            profileCard(
                onClick = {
                    viewModel.viewedProfile.value = profile
                    viewModel.showSheet()
                },
                profile = profile
            )
        }
    }
    if (showProfileSheet == true) {
        profileSheet(viewModel.viewedProfile.value, viewModel, sheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profileSheet(profile: Profile?, viewModel: HomeViewModel, sheetState: SheetState) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.hideSheet() },
        sheetState = sheetState
    ) {
        profileScreen(profile ?: Profile())
        println("showSheet Value: " + viewModel.showProfileSheet.value)
    }
}