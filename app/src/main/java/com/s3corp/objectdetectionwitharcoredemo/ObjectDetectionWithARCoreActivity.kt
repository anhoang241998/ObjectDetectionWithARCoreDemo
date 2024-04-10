package com.s3corp.objectdetectionwitharcoredemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.s3corp.objectdetectionwitharcoredemo.ui.theme.ObjectDetectionWithARCoreDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ObjectDetectionWithARCoreActivity : ComponentActivity() {

    private val objectDetectionWithARCoreViewModel: ObjectDetectionWithARCoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ObjectDetectionWithARCoreDemoTheme {
                objectDetectionWithARCoreViewModel.ObjectDetectionWithARCoreDemoNavHost()
            }
        }
    }
}