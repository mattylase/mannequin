package industries.laser.mannequin

import android.widget.EditText
import industries.laser.mannequin.validators.None
import industries.laser.mannequin.validators.Validator

class Mannequin {

    val validationList = mutableListOf<MannequinValidated>()

    infix fun validates(editText: EditText?): MannequinValidated {
        val mannequinValidated = MannequinValidated(editText)
        validationList.add(mannequinValidated)
        return mannequinValidated
    }
}

class MannequinValidated(val view: EditText?) {
    private var validationEvent: ValidationEvent = ValidationEvent.ON_FOCUS_LOST
    private var validationMethod: ValidationMethod = ValidationMethod.NOT_EMPTY
    private var validator: Validator = ::None

    infix fun during(validationEvent: ValidationEvent): MannequinValidated {
        this.validationEvent = validationEvent
        return this
    }

//    infix fun via(validationMethod: ValidationMethod): MannequinValidated {
//        this.validationMethod = validationMethod
//        return this
//    }

    infix fun via(function: MannequinValidated.() -> Boolean): MannequinValidated {
        this.validator = function
        return this
    }
}


enum class ValidationEvent {
    ON_KEY,
    ON_FOCUS_LOST
}

enum class ValidationMethod {
    NOT_EMPTY,
    NUMERIC,
    ALPHABETICAL
}