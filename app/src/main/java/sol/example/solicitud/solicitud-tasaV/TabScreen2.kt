package com.example.prot2.`solicitud-tasaV`

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true,
    device = "id:pixel_5",
    showSystemUi = true)
@Composable
fun HistoricoTasaContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "clasificacion interna :",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = "C",
                onValueChange = {},
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                readOnly = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    disabledBorderColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TPP-Tasa Promedio Ponderado:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = "26.55555%",
                onValueChange = {},
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                readOnly = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(4.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    disabledBorderColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Histórico Tasa de Cliente",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 2.dp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        TableRow(
            cells = listOf("Encabezado 1", "Encabezado 2", "Encabezado 3"),
            columnWidths = listOf(100.dp, 100.dp, 100.dp),
            isHeader = true,
            rowIndex = 0
        )

        TableRow(
            cells = listOf("Dato 1", "Dato 2", "Dato 3"),
            columnWidths = listOf(100.dp, 100.dp, 100.dp),
            isHeader = false,
            rowIndex = 1
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Performance ADN",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                thickness = 2.dp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        TableRow(
            cells = listOf("Encabezado A", "Encabezado B", "Encabezado C"),
            columnWidths = listOf(100.dp, 100.dp, 100.dp),
            isHeader = true,
            rowIndex = 0
        )

        TableRow(
            cells = listOf("Valor A1", "Valor B1", "Valor C1"),
            columnWidths = listOf(100.dp, 100.dp, 100.dp),
            isHeader = false,
            rowIndex = 1
        )

        TableRow(
            cells = listOf("Valor A2", "Valor B2", "Valor C2"),
            columnWidths = listOf(100.dp, 100.dp, 100.dp),
            isHeader = false,
            rowIndex = 2
        )
    }
}

@Composable
fun TableRow(
    cells: List<String>,
    columnWidths: List<Dp>,
    isHeader: Boolean = false,
    rowIndex: Int = 0
) {
    val rowColor = if (isHeader) {
        Color(0xFFF77A27)
    } else {
        if (rowIndex % 2 == 0) Color(0xFFF3E4CD) else Color.White
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowColor)
    ) {
        cells.forEachIndexed { index, cellData ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .width(columnWidths.getOrElse(index) { 0.dp }),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = cellData,
                    style = if (isHeader) {
                        MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    } else {
                        MaterialTheme.typography.bodySmall
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
            if (index < cells.size - 1) {
                Spacer(
                    modifier = Modifier
                        .width(1.dp)
                        .height(20.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}
