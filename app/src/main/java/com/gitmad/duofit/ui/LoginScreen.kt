package com.gitmad.duofit.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gitmad.duofit.viewmodels.MainViewModel
import com.gitmad.duofit.viewmodels.Screen
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gitmad.duofit.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
//@Preview
fun LoginScreen2(
    mainViewModel: MainViewModel = viewModel(),
) {

    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    var email by rememberSaveable { mutableStateOf("") }

    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    var launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { authResult ->
            user = authResult.user
        },
        onAuthError = {
            user = null
        })
    val token = stringResource(id = R.string.default_web_client_id)
    val context = LocalContext.current

    Column {
        if (user == null) {
            Text("Not Logged In")
            Button(onClick = {
                val gso =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(token)
                        .requestEmail()
                        .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }) {
                Text("Sign in via Google")
            }
        } else {
            Text("Welcome ${user!!.displayName}")
            Button(onClick = {
                Firebase.auth.signOut()
                user = null
            }) {
                Text(text = "Sign out")
            }
        }
    }


}

@Composable
@Preview(showBackground = true)
fun LoginScreen(mainViewModel: MainViewModel = MainViewModel()) {
    var name by rememberSaveable { mutableStateOf("") }
    var image by rememberSaveable { mutableStateOf<Bitmap?>(null) }
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    var launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { authResult ->
            user = authResult.user
        },
        onAuthError = {
            user = null
        })
    val token = stringResource(id = R.string.default_web_client_id)
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user == null) {
            Text(
                text = "Welcome to DuoFit",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Connect with fitness enthusiasts and find your perfect workout partner.",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )


            Image(
                painter = painterResource(R.drawable.trainer),
                contentDescription = "Trainer",
                modifier = Modifier.size(300.dp)
            )

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    val gso =
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()
                    val googleSignInClient = GoogleSignIn.getClient(context, gso)
                    launcher.launch(googleSignInClient.signInIntent)
                },
            ) {
                Text("Google Login")
            }
        } else {
            mainViewModel.navigateTo(Screen.NameQuiz)
        }
    }
}


@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
) : ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)

            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }
}

@Composable
fun ProgressDots(totalDots: Int, currentDot: Int) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 0 until totalDots) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (i == currentDot) Color.Gray else Color.LightGray)
            )
        }
    }
}