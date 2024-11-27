
package sol.example.solicitud.`solicitud-tasaV`

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PropuestaNegociacionViewModel : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> get() = _errorMessage

    fun validateJustificationText(text: String) {
        val wordCount = text.trim().split("\\s+".toRegex()).size
        val charCount = text.trim().length

        _errorMessage.value = when {
            wordCount < 2 -> "La justificación debe tener al menos 2 palabras"
            charCount < 2 -> "La justificación debe tener al menos 2 caracteres"
            else -> ""
        }
    }

    fun isJustificationValid(): Boolean {
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


class DesplegableEntidadFinancieraViewModel : ViewModel() {

    private val _nombreEntidad = mutableStateOf("")
    val nombreEntidad: State<String> get() = _nombreEntidad
    fun onNombreEntidadChanged(newValue: String) {
        _nombreEntidad.value = newValue
    }

    private val _tea = mutableStateOf("")
    val tea: State<String> get() = _tea
    fun onTeaChanged(newValue: String) {
        _tea.value = newValue
    }

    private val _amount = mutableStateOf("")
    val amount: State<String> get() = _amount
    fun onAmountChanged(newValue: String) {
        _amount.value = newValue
    }

    private val _comment = mutableStateOf("")
    val comment: State<String> get() = _comment
    fun onCommentChanged(newValue: String) {
        _comment.value = newValue
    }
}






class GuardarSolicitudViewModel(
    private val propuestaNegociacionViewModel: PropuestaNegociacionViewModel
) : ViewModel() {

    private val _justificationText = mutableStateOf("")
    val justificationText: State<String> get() = _justificationText

    private val _nombreEntidad = mutableStateOf("")
    val nombreEntidad: State<String> get() = _nombreEntidad

    private val _tea = mutableStateOf("")
    val tea: State<String> get() = _tea

    private val _amount = mutableStateOf("")
    val amount: State<String> get() = _amount

    private val _isButtonEnabled = mutableStateOf(false)
    val isButtonEnabled: State<Boolean> get() = _isButtonEnabled

    private fun validateFields() {
        _isButtonEnabled.value = _justificationText.value.isNotBlank() &&
                _nombreEntidad.value.isNotBlank() &&
                _tea.value.isNotBlank() &&
                _amount.value.isNotBlank() &&
                propuestaNegociacionViewModel.isJustificationValid()
    }

    fun onJustificationTextChange(text: String) {
        _justificationText.value = text
        propuestaNegociacionViewModel.validateJustificationText(text)
        validateFields()
    }

    fun onNombreEntidadChange(text: String) {
        _nombreEntidad.value = text
        validateFields()
    }

    fun onTeaChange(text: String) {
        _tea.value = text
        validateFields()
    }

    fun onAmountChange(text: String) {
        _amount.value = text
        validateFields()
    }

    fun guardarSolicitud() {
        viewModelScope.launch {
            println("Solicitud guardada exitosamente")
        }
    }
}

