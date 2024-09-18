package com.example.lab5robertonajera

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme

class EventDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                EventDetailScreen("", rememberNavController())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(eventId: String, navController: NavController) {
    val concert = ConcertRepository.getConcertById(eventId)

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)

    var isFavorite by remember { mutableStateOf(concert?.isFavorite ?: false) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Detalles del Evento") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    EventDetailContent(concert)
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        isFavorite = !isFavorite
                        val updatedConcert = concert?.copy(isFavorite = isFavorite)
                        if (updatedConcert != null) {
                            ConcertRepository.updateConcert(updatedConcert)
                        } // Update the repository
                        val editor = sharedPreferences.edit()
                        editor.putBoolean(eventId, isFavorite).apply()
                    }) {
                        Text(text = if (isFavorite) "Remove Favorite" else "Favorite")
                    }
                    Button(onClick = { }) {
                        Text(text = "Buy")
                    }
                }
            }
        )
    }
}

@Composable
fun EventDetailContent(concert: Concert?) {
    // Replace with your own image resource
    val imageResource = concert?.imageRes;

    imageResource?.let { painterResource(id = it) }?.let {
        Image(
        painter = it,
        contentDescription = "Event Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 16.dp)
    )
    }

    concert?.title?.let { Text(text = it, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) }

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

    if (concert != null) {
        Text(
            text =  concert.supportingText,
            style = MaterialTheme.typography.bodyMedium
        )
    }

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

