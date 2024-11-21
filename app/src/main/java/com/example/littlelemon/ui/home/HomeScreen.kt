package com.example.littlelemon.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.HeroSection
import com.example.littlelemon.ui.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    appDataBase: AppDatabase,
) {
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>("All") }
    val databaseMenuItems by appDataBase.menuItemDao().getAll().observeAsState(emptyList())
    var orderMenuItems by remember { mutableStateOf(false) }
    val categories = listOf("All") + databaseMenuItems
        .map { it.category }
        .distinct()

    val menuItems = if (orderMenuItems) {
        databaseMenuItems.sortedBy { it.title }
    } else {
        databaseMenuItems
    }
    val filteredMenuItems = menuItems
        .filter {
            it.title.contains(searchPhrase, ignoreCase = true)
        }
        .filter {
            selectedCategory == "All" || it.category == selectedCategory
        }

    Scaffold(
        topBar = {
            TopBar(navController = navController, showProfileIcon = true)
        }
    ) { innerPadding ->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            HeroSection(
                searchPhrase = searchPhrase,
                onSearchPhraseChange = { searchPhrase = it }
            )
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(20.dp, top = 15.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = "Order for Delivery".uppercase()
            )
            CategoryList(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryClick = { selectedCategory = it })

            MenuItems(filteredMenuItems)
        }
    }
}

