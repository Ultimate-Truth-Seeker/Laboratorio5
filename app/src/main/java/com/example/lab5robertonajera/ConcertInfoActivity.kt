package com.example.lab5robertonajera

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lab5robertonajera.ui.theme.TodoEventoTheme
import kotlinx.coroutines.launch

class ConcertInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoEventoTheme {
                ConcertInfoLayout(rememberNavController())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConcertInfoLayout(navController: NavHostController){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TodoEventos") },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    modifier = Modifier.background(color = Color(0xFF6200EE)),
                )
            }, content = { innerPadding ->
                ConcertInfoScreen(navController)
            }
        )
    }
}

@Composable
fun ConcertInfoScreen(navController: NavHostController) {
    val favoriteConcerts = remember { ConcertRepository.getFavoritesList() }
    val allConcerts = remember { ConcertRepository.concertList }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (favoriteConcerts.isNotEmpty()) {
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
                        ConcertCard(concert, Modifier.weight(1f)) {
                            navController.navigate("eventDetail/${concert.id}")

                        }
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
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
                    ConcertCard(concert, Modifier.weight(1f)) {
                        navController.navigate("eventDetail/${concert.id}")
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun FavoritesScreen(navController: NavHostController) {
    val favoriteConcerts = remember { ConcertRepository.getFavoritesList() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Your favorites",
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
                    ConcertCard(concert, Modifier.weight(1f)){
                        navController.navigate("eventDetail/${concert.id}")

                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ConcertCard(concert: Concert, modifier: Modifier = Modifier, function: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = function
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
    val imageRes: Int,
    val id: String,
)

// Singleton de eventos para el programa
object ConcertRepository {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)
    }
    fun favorite(id: String): Boolean {
        try {
            return sharedPreferences.getBoolean(id, false)
        } catch (_: Exception) {

        }
        return false
    }
    val concertList = mutableStateListOf(
        Concert("Title 1 Showcase", "Supporting text", R.drawable.concert_image1, 1.toString()),
        Concert("Title 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sit amet pellentes.", R.drawable.concert_image2, 2.toString() ),
        Concert("Title 3", "Supporting text", R.drawable.concert_image3, 3.toString()),
        Concert("Title 4", "Supporting text", R.drawable.concert_image4, 4.toString())
    )
    fun getConcertById(id: String): Concert? {
        return concertList.find { it.id == id }
    }
    fun getFavoritesList(): List<Concert> {
        return concertList.filter { favorite(it.id) }
    }

}

@Composable
fun DrawerContent() {
    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(16.dp)
        .background(color = Color.White)
        .width(128.dp)) {
        Text(
            text = "Navigation Drawer",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            )
        Text(text = "Opción 1", modifier = Modifier.padding(8.dp))
        Text(text = "Opción 2", modifier = Modifier.padding(8.dp))
        Text(text = "Opción 3", modifier = Modifier.padding(8.dp))
    }
}