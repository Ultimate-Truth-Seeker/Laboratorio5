package com.example.lab5robertonajera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme

class EventDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    EventDetailScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Event Details") },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                EventDetailContent()
            }
        }
    )
}

@Composable
fun EventDetailContent() {
    // Replace with your own image resource
    val imageResource = R.drawable.event_image

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Event Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp)
    )

    Text(text = "Event Name", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Location", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Date", style = MaterialTheme.typography.bodyMedium)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(text = "About", style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold))

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sit amet pellentes.",
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(24.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {  }) {
            Text(text = "Favorite")
        }
        Button(onClick = {  }) {
            Text(text = "Buy")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventDetailScreenPreview() {
    TodoEventoTheme {
        EventDetailScreen()
    }
}