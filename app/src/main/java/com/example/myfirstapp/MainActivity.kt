package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirstAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    val textVariants = listOf(
        "Всем привет!",
        "<3",
        "Я не знаю что ещё написать",
        "Во Flutter по-другому"
    )

    val currentTextIndex = remember { mutableStateOf(0) }
    val clickCounter = remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = textVariants[currentTextIndex.value],
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                currentTextIndex.value = (currentTextIndex.value + 1) % textVariants.size
                clickCounter.value++
            }
        ) {
            Text("Изменить текст", fontSize = 16.sp)
        }

        Text(
            text = getCounterText(clickCounter.value),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 32.dp)
        )
    }
}

private fun getCounterText(count: Int): String {
    return when {
        count % 10 == 1 && count % 100 != 11 -> "Вы нажали $count раз"
        count % 10 in 2..4 && count % 100 !in 12..14 -> "Вы нажали $count раза"
        else -> "Вы нажали $count раз"
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstAppTheme {
        GreetingScreen()
    }
}