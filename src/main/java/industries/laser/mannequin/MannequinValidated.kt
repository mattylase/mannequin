package industries.laser.mannequin

import android.view.View
import industries.laser.mannequin.validators.Validator

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
