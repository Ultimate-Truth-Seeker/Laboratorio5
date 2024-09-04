package com.example.lab5robertonajera

import com.example.lab5robertonajera.ui.theme.TodoEventoTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class PlacesListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PlacesListScreen()
                }
            }
        }
    }
}

data class Place(val name: String, val location: String, val imageResId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen() {
    val places = listOf(
        Place("LA Hall", "Guns and Roses Los Angeles", R.drawable.place_image1),
        Place("Denver Hall", "Guns and Roses Denver", R.drawable.place_image2),
        Place("Maddison Square Garden", "Guns and Roses New York", R.drawable.place_image3)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lugares") },
            )
        },
        content = { padding ->
            LazyColumn(contentPadding = padding) {
                items(places) { place ->
                    PlaceItem(place = place)
                }
            }
        }
    )
}

@Composable
fun PlaceItem(place: Place) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp).clickable {  }) {
            Image(
                painter = painterResource(id = place.imageResId),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = place.location, style = MaterialTheme.typography.titleLarge)
                Text(text = place.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesListScreenPreview() {
    TodoEventoTheme {
        PlacesListScreen()
    }
}
