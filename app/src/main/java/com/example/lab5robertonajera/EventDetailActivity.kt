package com.example.lab5robertonajera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
                title = { Text(text = "Detalles del Evento") },
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
        },
        bottomBar = {
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

    Text(text = "Título", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DateWithIcon()
        Text(text = "Hora", style = MaterialTheme.typography.bodyMedium)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(text = "About", style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold))

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sit amet pellentes.",
        style = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(24.dp))

}
@Composable
fun DateWithIcon() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .clickable {  }
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Calendar Icon"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Fecha", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun EventDetailScreenPreview() {
    TodoEventoTheme {
        EventDetailScreen()
    }
}