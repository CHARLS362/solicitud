package com.example.prot2.`solicitud-tasaV`


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import sol.example.solicitud.`solicitud-tasaV`.InteresFormViewModel
import sol.example.solicitud.`solicitud-tasaV`.PropuestaNegociacionViewModel
import androidx.compose.ui.focus.onFocusChanged


@Preview(showBackground = true,
    device = "id:pixel_5",
    showSystemUi = true)


@Composable
fun PropuestaDeNegociacionContent(viewModel: PropuestaNegociacionViewModel = PropuestaNegociacionViewModel()) {
    val scrollState = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("LISTA DE ELEMENTOS") }
    val options = listOf("Opción 1", "Opción 2", "Opción 3")

    var justificationText by remember { mutableStateOf("") }
    val errorMessage by viewModel.errorMessage
    val padding = 16.dp
    var menuWidth by remember { mutableStateOf(0.dp) }

    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        errorMessage.isNotEmpty() && !isFocused -> Color.Red
        errorMessage.isEmpty() && justificationText.isNotEmpty() -> Color.Green
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Motivo/Tipo:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .onGloballyPositioned { coordinates ->
                        menuWidth = coordinates.size.width.dp
                    }
                    .clickable { expanded = !expanded }
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedOption,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon",
                        tint = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Justificación Para la negociación de la Tasa:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = justificationText,
                onValueChange = { text ->
                    justificationText = text
                    viewModel.validateJustificationText(text)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                placeholder = {
                    Text(
                        text = "Escriba su justificación (mínimo 5 palabras y 5 caracteres)",
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    errorBorderColor = Color.Red,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                ),
                isError = errorMessage.isNotEmpty() && !isFocused 
            )

            if (errorMessage.isNotEmpty() && !isFocused) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            } else if (justificationText.isNotEmpty() && errorMessage.isEmpty()) {
                Text(
                    text = "Justificación válida",
                    color = Color.Green,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            InteresFormDesign()
        }

        if (expanded) {
            Popup(
                alignment = Alignment.TopStart,
                offset = IntOffset(0, 200),
                properties = PopupProperties(focusable = true),
                onDismissRequest = { expanded = false }
            ) {
                Box(
                    modifier = Modifier
                        .width(menuWidth)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                ) {
                    Column {
                        options.forEach { option ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedOption = option
                                    expanded = false
                                },
                                text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = selectedOption == option,
                                            onClick = {
                                                selectedOption = option
                                                expanded = false
                                            },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color(0xFF6200EE),
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(
                                            text = option,
                                            fontSize = 16.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InteresFormDesign(viewModel: InteresFormViewModel = InteresFormViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Normal",
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.minValue.value,
                onValueChange = {},
                label = { Text("Min.") },
                modifier = Modifier.weight(1f),
                enabled = false
            )

            OutlinedTextField(
                value = viewModel.maxValue.value,
                onValueChange = {},
                label = { Text("Max.") },
                modifier = Modifier.weight(1f),
                enabled = false
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.tasaMoratoria.value,
                onValueChange = {},
                label = { Text("T. Moratoria (%)") },
                modifier = Modifier.weight(1f),
                enabled = false
            )

            OutlinedTextField(
                value = viewModel.tasaInteres.value,
                onValueChange = {},
                label = { Text("T. Interés (TEA)") },
                modifier = Modifier.weight(1f),
                enabled = false
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Aquí puedes llamar a otros componentes, como EntidadExternaProponente si es necesario
    EntidadExternaProponente()
}

@Composable
fun EntidadExternaProponente() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Entidad Externa Proponente",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(5.dp))

        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(Color.Black)
        )
    }
    Spacer(modifier = Modifier.height(5.dp))
    DesplegableEntidadFinanciera()
}

@Composable
fun DesplegableEntidadFinanciera() {
    var expanded by remember { mutableStateOf(false) }
    var textInput by remember { mutableStateOf("") }
    var tea by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Nombre de la Entidad Financiera:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text(" Ingresa el nombre de la entidad") },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = !expanded },
                        tint = Color.Black
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "T. Interés (TEA):",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = tea,
                        onValueChange = { tea = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Text(
                        text = "Importe:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Text(
                        text = "Comentario:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = comment,
                        onValueChange = { comment = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }
    }
}

