package industries.laser.mannequin

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

class Mannequin {
    private val validationList = mutableListOf<MannequinValidated<*>>()

    infix fun validates(editText: EditText): MannequinValidated<String> {
        return MannequinValidated<String>(editText).apply {
            validationList.add(this)
        }
    }

    @Suppress("unchecked_cast")
    fun watch() {
        validationList.forEach { validated ->
            when (validated.view) {
                is EditText -> listenToEditText(validated as MannequinValidated<String>)
            }
        }
    }

    private fun listenToEditText(validated: MannequinValidated<String>) {
        val editText = validated.view as EditText
        when (validated.event) {
            ValidationEvent.OnKey -> editText.doAfterTextChanged {
                validated.validator(it.toString())
            }
        }
    }

    companion object {
        const val TAG = "Mannequin"
    }
}
