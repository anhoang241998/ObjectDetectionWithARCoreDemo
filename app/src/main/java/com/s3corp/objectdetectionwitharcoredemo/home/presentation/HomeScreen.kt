package com.s3corp.objectdetectionwitharcoredemo.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.s3corp.objectdetectionwitharcoredemo.common.utils.Pixel5Preview

@Composable
fun HomeScreen(
    onMoveToDetailTapped: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Button(onClick = onMoveToDetailTapped) {
            Text(text = "To Detail")
        }
    }
}

@Pixel5Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onMoveToDetailTapped = {})
}