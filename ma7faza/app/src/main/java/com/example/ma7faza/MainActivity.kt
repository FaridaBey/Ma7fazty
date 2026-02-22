package com.example.ma7faza

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ma7faza.ui.theme.Ma7fazaTheme
import com.example.ma7faza.navigation.NavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ma7fazaTheme {
                NavGraph()
                }
            }
        }
    }