package com.example.littlelemon.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.MenuItemRoom

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(menuItems: List<MenuItemRoom> = listOf()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        itemsIndexed(menuItems) { _, menuItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(

                ) {
                    Text(
                        text = menuItem.title,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = menuItem.description,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .padding(top = 5.dp, bottom = 5.dp)
                    )
                    Text(
                        text = "£${menuItem.price}",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                GlideImage(
                    model = menuItem.image,
                    contentDescription = "Menu Image",
                    modifier = Modifier
                        .size(100.dp)
                        .size(100.dp),
                )

            }
        }
    }
}
