package sol.example.solicitud

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import sol.example.solicitud.`solicitud-tasaV`.SolicitudScreen
import sol.example.solicitud.ui.theme.SolicitudTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SolicitudTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    SolicitudScreen()
                }
            }
        }
    }
}

