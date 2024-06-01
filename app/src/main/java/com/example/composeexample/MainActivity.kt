package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composeexample.ui.theme.ComposeExampleTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExampleTheme {
                var text by rememberSaveable {
                    mutableStateOf("")
                }
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Message) {
                    composable<Message> {
                        MessageScreen(text = text, onValueChange = { text = it }) {
                            navController.navigate(Greeting(name = text))
                        }
                    }

                    composable<Greeting> {
                        val greeting = it.toRoute<Greeting>()
                        WishScreen(greeting = greeting)
                    }
                }
            }
        }
    }
}

@Composable
fun MessageScreen(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = "Enter your name")
            },
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            onClick()
        }) {
            Text(text = "Go")
        }
    }
}

@Composable
fun WishScreen(
    modifier: Modifier = Modifier,
    greeting: Greeting
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Welcome ${greeting.name}!", modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun WishScreenPreview() {
    ComposeExampleTheme {
        WishScreen(greeting = Greeting("Mubarak"))
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageScreenPreview() {
    ComposeExampleTheme {
        MessageScreen(text = "Enter your name", onValueChange = {}) {}
    }
}

@Serializable
object Message

@Serializable
data class Greeting(
    val name: String
)