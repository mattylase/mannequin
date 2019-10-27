package industries.laser.mannequin

import android.view.View
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import industries.laser.mannequin.validators.Validator

class Mannequin {
    val validationList = mutableListOf<MannequinValidated<*>>()

    infix fun validates(editText: EditText): MannequinValidated<String> {
        val mannequinValidated = MannequinValidated<String>(editText)
        validationList.add(mannequinValidated)
        return mannequinValidated
    }

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
            ValidationEvent.ON_KEY -> editText.doAfterTextChanged {
                validated.validator(it.toString())
            }
        }
    }

    companion object {
        const val TAG = "Mannequin"
    }
}

class MannequinValidated<T>(val view: View) {
    var event: ValidationEvent? = null
    lateinit var validator: Validator<T>

    infix fun during(validationEvent: ValidationEvent): MannequinValidated<T> {
        this.event = validationEvent
        return this
    }

    infix fun via(function: T.() -> Boolean): MannequinValidated<T> {
        this.validator = function
        return this
    }
}

enum class ValidationEvent {
    ON_KEY,
    ON_FOCUS_LOST
}
