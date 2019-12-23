package industries.laser.mannequin

import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged

class Mannequin {
    private val validationList = mutableListOf<MannequinValidated<*>>()
    private val submission: MannequinSubmission = MannequinSubmission(this)
    private var onSubmissionFinalValidation: (() -> Unit)? = null

    infix fun validates(editText: EditText): MannequinValidated<String> {
        return MannequinValidated<String>(editText).apply {
            validationList.add(this)
        }
    }

    infix fun validates(checkBox: CheckBox): MannequinValidated<Boolean> {
        return MannequinValidated<Boolean>(checkBox).apply {
            validationList.add(this)
        }
    }

    internal fun onSubmit() {
        validationList.forEach { validated ->
            if (validated.event == ValidationEvent.Submission) {
                validated.validationFunction?.invoke()
            }

            if (!validated.isValid) {
                return
            }
        }

        onSubmissionFinalValidation?.invoke()
    }

    @Suppress("unchecked_cast")
    fun watch(submitView: View, onValid: () -> Unit) {
        submission.registerSubmitView(submitView)
        onSubmissionFinalValidation = onValid

        validationList.forEach { validated ->
            when (validated.view) {
                is EditText -> prepareEditTextValidation(validated as MannequinValidated<String>)
                is CheckBox -> prepareCheckBoxValidation(validated as MannequinValidated<Boolean>)
            }
        }
    }

    private fun prepareEditTextValidation(validated: MannequinValidated<String>) {
        val editText = validated.view as EditText

        validated.validationFunction = {
            validated.validator?.let { validator ->
                val valid = validator(editText.text.toString())
                if (validated.isValid != valid) {
                    validated.isValid = valid
                    validated.notifier?.invoke(editText, valid)
                }
            }
        }

        when (validated.event) {
            ValidationEvent.OnKey -> editText.doAfterTextChanged {
                validated.validationFunction?.invoke()
            }
            ValidationEvent.OnFocusLost -> editText.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    validated.validationFunction?.invoke()
                }
            }
        }
    }

    private fun prepareCheckBoxValidation(validated: MannequinValidated<Boolean>) {
        val checkBox = validated.view as CheckBox

        validated.validationFunction = {
            validated.validator?.let { validator ->
                val valid = validator(checkBox.isChecked)
                if (validated.isValid != valid) {
                    validated.isValid = valid
                    validated.notifier?.invoke(checkBox, valid)
                }
            }
        }
    }

    companion object {
        const val TAG = "Mannequin"
    }
}
