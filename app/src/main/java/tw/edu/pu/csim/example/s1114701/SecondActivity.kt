package tw.edu.pu.csim.example.s1114701

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.csim.example.s1114701.ui.theme.S1114701Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S1114701Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting2("Android")
                    Main2()
                }
            }
        }
    }
}

@Composable
fun FirstScreen1(navController: NavHostController){
    val context = LocalContext.current
    Column {
        Text(
            text = "「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。\n"
        )
        Text(
            text = "長按以下圖片，可以觀看愛心家園地圖",
            color = Color.Blue
        )
        Image(
            painter = painterResource(id = R.drawable.lovehome),
            contentDescription = "service",
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            var it = Intent(Intent.ACTION_VIEW)
                            it.data = Uri.parse("geo:0,0?q=台中市南屯區東興路一段450號")
                            context.startActivity(it)
                        },
                    )
                }
        )
    }
}

@Composable
fun SecondScreen2(navController: NavHostController) {
    val context = LocalContext.current
    Column {
        Text(
            text = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。\n"
        )
        Text(
            text = "雙擊以下圖片，可以觀看瑪利亞學園地圖",
            color = Color.Blue
        )
        Image(
            painter = painterResource(id = R.drawable.campus),
            contentDescription = "service",
            modifier = Modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            var it = Intent(Intent.ACTION_VIEW)
                            it.data = Uri.parse("geo:0,0?q=台中市北屯區經貿東路365號")
                            context.startActivity(it)
                        },
                    )
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main2() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    val activity = (context as Activity)
    var appear by remember { mutableStateOf(true) }
    S1114701Theme {
        // Ensure that the TopAppBar is included in the Surface
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.maria),
                            contentDescription = "button icon",
                            modifier = Modifier.size(120.dp)
                        )
                    },
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("簡介") },
                                onClick = {
                                    activity.finish()
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("主要機構") },
                                onClick = {
                                    navController.navigate("JumpSecond")
                                    showMenu = false
                                }
                            )
                        }
                    }
                )
                Text(text = "主要機構",color = Color.Red)

                Row {
                    Button(onClick = {
                        appear = false
                        navController.navigate("JumpFirst")
                    }) {
                        Text(text = "台中市愛心家園")
                    }
                    Button(onClick = {
                        appear = false
                        navController.navigate("JumpSecond")
                    }) {
                        Text(text = "瑪利亞學園")
                    }
                }

                NavHost(navController = navController, startDestination = "JumpFirst") {
                    composable("JumpFirst") {
                        FirstScreen1(navController = navController)
                    }
                    composable("JumpSecond") {
                        SecondScreen2(navController = navController)
                    }
                }
            }
        }
    }
}