package com.example.lab5robertonajera

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoEventoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Button(onClick = {
                            val intent = Intent(this@MainActivity, ConcertInfoActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "Go to Concert Info")
                        }

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, PlacesListActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "Go to Places List")
                        }

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, EventDetailActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "Go to Event Detail")
                        }

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text(text = "Go to Profile")
                        }
                    }
                }
            }
        }
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
    TodoEventoTheme {
        Greeting("Android")
    }
}