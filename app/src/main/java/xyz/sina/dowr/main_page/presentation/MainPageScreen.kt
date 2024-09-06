package xyz.sina.dowr.main_page.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import xyz.sina.dowr.R
import xyz.sina.dowr.core.navigation.InGameSettings
import xyz.sina.dowr.core.navigation.Tutorial

@Composable
fun MainPage(navController: NavHostController) {

    val showAlertDialog = remember { mutableStateOf(false) }
    if(showAlertDialog.value) { InfoAlertDialog(showAlertDialog = showAlertDialog)}

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                IconButton(modifier = Modifier.weight(1f), onClick = {
                    showAlertDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                IconButton(modifier = Modifier.weight(1f), onClick = {
                    navController.navigate(Tutorial)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_help),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(innerPadding), contentAlignment = Alignment.Center
        ) {
            Row() {
                Image(
                    modifier = Modifier.clickable { navController.navigate(InGameSettings) },
                    painter = painterResource(id = R.drawable.ic_play),
                    contentDescription = null,
                )
                IconButton(modifier = Modifier, onClick = {
                    navController.navigate(Tutorial)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_help),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

}

@Composable
fun InfoAlertDialog(
    showAlertDialog: MutableState<Boolean>
) {
    if (showAlertDialog.value) {
        AlertDialog(
            onDismissRequest = { showAlertDialog.value = false },
            title = { Text(modifier = Modifier.fillMaxWidth(), text = "درباره بازی", textAlign = TextAlign.Center)},
            text = { Text(modifier = Modifier.fillMaxWidth(),text = "نوشته شده با بدبختی", style = TextStyle(textDirection = TextDirection.Rtl))},
            confirmButton = {})
    }
}
