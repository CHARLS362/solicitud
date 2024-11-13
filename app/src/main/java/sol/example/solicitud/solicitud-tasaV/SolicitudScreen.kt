package sol.example.solicitud.`solicitud-tasaV`

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.prot2.`solicitud-tasaV`.HistoricoTasaContent
import com.example.prot2.`solicitud-tasaV`.PropuestaDeNegociacionContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudScreen() {
    Column {
        Header()

        TabBar()
    }
}

@ExperimentalMaterial3Api
@Composable
fun Header() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF005EB8),
            titleContentColor = Color.White
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SOLICITUD DE TASA NEGOCIABLE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    )
}

@Composable
fun TabBar() {
    val tabTitles = listOf("Propuesta de Negociación", "Histórico Tasa")

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }
    }

    when (selectedTabIndex) {
        0 -> PropuestaDeNegociacionContent()
        1 -> HistoricoTasaContent()
    }
}

@Preview(showBackground = true,
    device = "id:pixel_5",
    showSystemUi = true)
@Composable
fun PreviewSolicitudScreen() {
    SolicitudScreen()
}
