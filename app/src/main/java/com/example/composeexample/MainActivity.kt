package com.example.composeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composeexample.ui.theme.ComposeExampleTheme

/** CompositionLocal allows you to create tree-scoped named objects that can be used as an implicit way of passing data to the composable UI tree
 * rather than passing data explicitly to those composable that want data by using composable function
 * parameters */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeExampleTheme {
                val system = if (isSystemInDarkTheme()) {
                    System("Dark Theme")
                } else System("Light Theme") // conditionally change the value of the System

                Scaffold {
                    CompositionLocalProvider(LocalSystem provides system) { // provides the conditionally changing (system) value to the key of the compositionLocal
                        SystemDetail(modifier = Modifier.padding(it))
                    }
                }

            }
        }
    }
}

@Composable
fun SystemDetail(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = LocalSystem.current.theme, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailPreview() {
    ComposeExampleTheme {
        SystemDetail()
    }
}