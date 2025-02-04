package com.example.android_json_ontap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_json_ontap.ui.theme.Android_Json_OnTapTheme

class MainActivity : ComponentActivity() {
    private lateinit  var navHostController: NavHostController
    private var viewModel = ProductViewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_Json_OnTapTheme {
                navHostController = rememberNavController()
                NavGraph(navHostController = navHostController, viewModel = viewModel)
            }
        }
    }
}
