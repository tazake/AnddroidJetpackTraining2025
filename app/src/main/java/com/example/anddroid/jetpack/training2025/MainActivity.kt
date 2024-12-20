package com.example.anddroid.jetpack.training2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_FOLD
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anddroid.jetpack.training2025.ui.theme.AnddroidJetpackTraining2025Theme

//TODO : option + 上下で関数を範囲選択が可能
//TODO : そのまま、option + command + mで関数に切り出し

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnddroidJetpackTraining2025Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "top") {
                    composable("top") {
                        TopScreen(onClick = { navController.navigate("second") })
                    }
                    composable("second") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                Button(onClick = { navController.navigate("third") }) {
                                    Text("Second Screen")
                                }
                            }
                        }
                    }
                    composable("third") {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {
                                Button(onClick = { navController.popBackStack() }) {
                                    Text("Third Screen")
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
private fun TopScreen(onClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                // TODO : これわすれた1
                // paddingとbackgroundを変えると色が適用される範囲が変わる
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            SearchBox(onClick = onClick)
            PersonaTab()
        }
    }
}


@Composable
fun SearchBox(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        shape = CircleShape,
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 12.dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start, //なくてもいいけど明示的に指定しておきたい
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                //                            painter = painterResource(id = R.drawable.ic_launcher_background)
                painter = rememberVectorPainter(image = Icons.Default.Search),
                contentDescription = "Search",
                tint = Color.Red,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.search_label),
                fontWeight = FontWeight.W300,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.wrapContentSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonaTab() {
    var state by remember { mutableIntStateOf(0) } //TODO: これわすれた2
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = {
                        Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Primary tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
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
    AnddroidJetpackTraining2025Theme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, name = "small font", fontScale = 0.5f)
@Preview(showBackground = true, name = "normal font")
@Preview(showBackground = true, name = "large font", fontScale = 1.5f)
@Preview(device = "spec:width=720px,height=1280px,dpi=300", locale = "ja", fontScale = 1.3f)
@Preview(device = PIXEL_FOLD, locale = "ja", fontScale = 1.3f)
@Composable
fun SearchBoxPreview() {
    SearchBox(onClick = {})
}