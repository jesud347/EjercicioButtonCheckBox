package com.example.ejerciciobuttoncheckbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ejerciciobuttoncheckbox.ui.theme.EjercicioButtonCheckBoxTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioButtonCheckBoxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var showProgress by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var isTextVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showProgress) {
                Box(contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Botón presionado")
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    }
                }
            } else {
                Button(onClick = {
                    showProgress = true
                    coroutineScope.launch {
                        delay(5000)
                        showProgress = false
                    }
                }) {
                    Text(text = "Presionar")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (isTextVisible) {
                    Text(text = "Hola que tal")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isTextVisible,
                        onCheckedChange = { isTextVisible = it }
                    )
                    Text(text = "Activar")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Icono de información",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                var isSwitchChecked by rememberSaveable { mutableStateOf(false) }
                var selectedOption by rememberSaveable { mutableStateOf<String?>(null) }
                var displayText by rememberSaveable  { mutableStateOf("Activar opciones") }

                Switch(
                    checked = isSwitchChecked,
                    onCheckedChange = {
                        isSwitchChecked = it
                        if (!it) {
                            displayText = "Activar opciones"
                            selectedOption = null
                        }
                    }
                )

                Text(text = displayText)

                if (isSwitchChecked) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedOption == "Opción 1",
                                onClick = {
                                    selectedOption = "Opción 1"
                                    displayText = "Opción 1"
                                }
                            )
                            Text(text = "Opción 1")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedOption == "Opción 2",
                                onClick = {
                                    selectedOption = "Opción 2"
                                    displayText = "Opción 2"
                                }
                            )
                            Text(text = "Opción 2")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedOption == "Opción 3",
                                onClick = {
                                    selectedOption = "Opción 3"
                                    displayText = "Opción 3"
                                }
                            )
                            Text(text = "Opción 3")
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            var currentImageIndex by rememberSaveable { mutableStateOf(0) }
            val images = listOf(
                R.drawable.a1,
                R.drawable.a2,
                R.drawable.a3
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        currentImageIndex = (currentImageIndex + 1) % images.size
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = images[currentImageIndex]),
                    contentDescription = "Imagen",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

