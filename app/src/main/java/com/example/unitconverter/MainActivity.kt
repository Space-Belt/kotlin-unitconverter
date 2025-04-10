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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

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

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("m") }
    var outputUnit by remember { mutableStateOf("m") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    val CustomTextStyle = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp,
        color = Color.Magenta
    )

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color.Blue,          // 파란색 적용
                fontWeight = FontWeight.Bold // 굵기 설정
            )
        )

        ReusableSpacer(20)
        OutlinedTextField(
            modifier = Modifier.padding(bottom = 20.dp  ),
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = { (Text("입력해주세요")) }
            )
        Row {
            Box {
                Button(onClick = {
                    iExpanded = !iExpanded
                }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "아래 화살표" )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = !iExpanded}) {
                    DropdownMenuItem(text = {Text("cm")}, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "cm"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("m")}, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "m"
                        conversionFactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("mm")}, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "mm"
                        conversionFactor.value = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("km")}, onClick = {
                        iExpanded = !iExpanded
                        inputUnit = "km"
                        conversionFactor.value = 1000.0
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    oExpanded = !oExpanded
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "아래 화살표" )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = !oExpanded }) {
                    DropdownMenuItem(text = {Text("cm")}, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "cm"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("m")}, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "m"
                        oConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("mm")}, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "mm"
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                    DropdownMenuItem(text = {Text("km")}, onClick = {
                        oExpanded = !oExpanded
                        outputUnit = "km"
                        oConversionFactor.value = 1000.0
                        convertUnits()
                    })
                }
            }
        }
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.titleMedium
            )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}