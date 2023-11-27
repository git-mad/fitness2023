package com.gitmad.duofit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.models.Profile
import com.gitmad.duofit.ui.components.profileCard
import com.gitmad.duofit.viewmodels.HomeViewModel
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val profileList by viewModel.fetchedProfileList.observeAsState(initial = mutableListOf())
    val showProfileSheet by viewModel.showProfileSheet.observeAsState()
    val isFetching by viewModel.isFetching.observeAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isFetching!!,
        onRefresh = { viewModel.resetList() }
    )
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    viewModel.listUpToDate.observe(LocalLifecycleOwner.current) {
        if (it == false) {
            viewModel.fetchList()
        }
    }


    Box(Modifier.pullRefresh(pullRefreshState)) {
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
        PullRefreshIndicator(
            refreshing = isFetching!!,
            state = pullRefreshState,
            Modifier.align(
                Alignment.TopCenter
            )
        )
    }
    if (showProfileSheet == true) {
        ProfileSheet(viewModel.viewedProfile.value, viewModel, sheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSheet(profile: Profile?, viewModel: HomeViewModel, sheetState: SheetState) {
    ModalBottomSheet(
        onDismissRequest = { viewModel.hideSheet() },
        sheetState = sheetState
    ) {
        ProfileScreen(
            profile ?: Profile()
        )
        println("showSheet Value: " + viewModel.showProfileSheet.value)
    }
}