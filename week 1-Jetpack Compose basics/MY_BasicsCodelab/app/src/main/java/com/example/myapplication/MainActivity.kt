package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MyApp()
            }
        }
    }
}
@Composable
private fun MyApp() {

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom=extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = "$name!")
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.padding(top=4.dp)) {
                Text(if(expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier =Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to My First Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Enter")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
private fun OnboardingPreview() {
    MyApplicationTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        Greetings()
    }
}