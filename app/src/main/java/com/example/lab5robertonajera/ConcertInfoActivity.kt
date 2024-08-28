package com.example.lab5robertonajera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme

class ConcertInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ConcertListScreen()
                }
            }
        }
    }
}

data class Concert(val name: String, val location: String, val imageResId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcertListScreen() {
    val concerts = listOf(
        Concert("Guns And Roses LA", "LA Hall", R.drawable.concert_image1),
        Concert("Guns and Roses Denver", "Denver Hall", R.drawable.concert_image2),
        Concert("Guns and Roses New York", "Maddison Square Garden", R.drawable.concert_image3)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Upcoming Concerts") },
            )
        },
        content = { padding ->
            LazyColumn(contentPadding = padding) {
                items(concerts) { concert ->
                    ConcertItem(concert = concert)
                }
            }
        }
    )
}

@Composable
fun ConcertItem(concert: Concert) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = concert.imageResId),
                contentDescription = concert.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = concert.name, style = MaterialTheme.typography.titleLarge)
                Text(text = concert.location, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConcertListScreenPreview() {
    TodoEventoTheme {
        ConcertListScreen()
    }
}
