package com.emekdev.myapplication.screens.listscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        var query = remember { mutableStateOf("") }

        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = query.value,
                    placeholder = { Text("Chair") },
                    onQueryChange = {q -> query.value = q},
                    onSearch = {},
                    expanded = false,
                    onExpandedChange = {},
                    leadingIcon = {Icon(Icons.Default.Search, "")},
                    trailingIcon = {Icon(Icons.AutoMirrored.Default.ArrowForward, "")}
                )
            },
            expanded = false,
            onExpandedChange = {},
            shadowElevation = 16.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0..10) {
                    Text("Hello World! $i")
                }
            }
        }
    }
}