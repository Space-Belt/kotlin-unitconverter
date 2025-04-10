package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun ReusableSpacer(cusotomHeight: Int) {
    Spacer(modifier = Modifier.height(cusotomHeight.dp))
}

@Composable
fun UnitConverter() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter")

        ReusableSpacer(100)
        OutlinedTextField(
            modifier = Modifier.padding(),
            value = "",
            onValueChange = {},
            )
        Row {
            Box {
                Button(onClick = { }) {
                    Text("선택")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "아래 화살표" )
                }
                DropdownMenu(expanded = false, onDismissRequest = {/* 드롭다운 실행시 */}) {
                    DropdownMenuItem(text = {Text("cm")}, onClick = {})
                    DropdownMenuItem(text = {Text("m")}, onClick = {})
                    DropdownMenuItem(text = {Text("mm")}, onClick = {})
                    DropdownMenuItem(text = {Text("CM")}, onClick = {})
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { }) {
                    Text("선택")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "아래 화살표" )
                }
                DropdownMenu(expanded = true, onDismissRequest = {/* 드롭다운 실행시 */}) {
                    DropdownMenuItem(text = {Text("cm")}, onClick = {})
                    DropdownMenuItem(text = {Text("m")}, onClick = {})
                    DropdownMenuItem(text = {Text("mm")}, onClick = {})
                    DropdownMenuItem(text = {Text("CM")}, onClick = {})
                }
            }
        }
        Text("Result:")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}