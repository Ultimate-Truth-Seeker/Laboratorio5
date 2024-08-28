package com.example.lab5robertonajera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme

class ConcertInfoActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("TodoEventos") },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.place_image1),
                                        contentDescription = "Menu"
                                    )
                                }
                            },
                            modifier = Modifier.background(color = Color(0xFF6200EE)),
                        )
                    }, content = {
                        ConcertInfoScreen()
                    }
                )
            }
        }
    }
}

@Composable
fun ConcertInfoScreen() {
    val favoriteConcerts = remember { getSampleConcerts() }
    val allConcerts = remember { getSampleConcerts() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "\n\nYour favorites",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(favoriteConcerts.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { concert ->
                    ConcertCard(concert, Modifier.weight(1f))
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Text(
                text = "All Concerts",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        items(allConcerts.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { concert ->
                    ConcertCard(concert, Modifier.weight(1f))
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concert, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = concert.imageRes),
                contentDescription = concert.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = concert.title, fontWeight = FontWeight.Bold)
            Text(text = concert.supportingText, color = Color.Gray)
        }
    }
}

data class Concert(
    val title: String,
    val supportingText: String,
    val imageRes: Int
)

fun getSampleConcerts(): List<Concert> {
    return listOf(
        Concert("Title 1", "Supporting text", R.drawable.concert_image1),
        Concert("Title 2", "Supporting text", R.drawable.concert_image2),
        Concert("Title 3", "Supporting text", R.drawable.concert_image3),
        Concert("Title 4", "Supporting text", R.drawable.concert_image3)
    )
}