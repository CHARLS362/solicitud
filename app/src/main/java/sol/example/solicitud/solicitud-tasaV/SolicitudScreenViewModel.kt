
package sol.example.solicitud.`solicitud-tasaV`

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PropuestaNegociacionViewModel : ViewModel() {

    // Estado para el mensaje de error
    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> get() = _errorMessage

    // Método para validar si el texto contiene al menos 5 palabras y 5 caracteres
    fun validateJustificationText(text: String) {
        val wordCount = text.trim().split("\\s+".toRegex()).size
        val charCount = text.trim().length

        // Condiciones para el mensaje de error
        _errorMessage.value = when {
            wordCount < 5 -> "La justificación debe tener al menos 5 palabras"
            charCount < 5 -> "La justificación debe tener al menos 5 caracteres"
            else -> ""
        }
    }

    // Método para verificar si la justificación es válida
    fun validateJustification(): Boolean {
        return _errorMessage.value.isEmpty()
    }
}


class InteresFormViewModel : ViewModel() {
    private val _minValue = mutableStateOf("3%")
    val minValue: State<String> get() = _minValue

    private val _maxValue = mutableStateOf("10%")
    val maxValue: State<String> get() = _maxValue

    private val _tasaMoratoria = mutableStateOf("5%")
    val tasaMoratoria: State<String> get() = _tasaMoratoria

    private val _tasaInteres = mutableStateOf("7%")
    val tasaInteres: State<String> get() = _tasaInteres
}