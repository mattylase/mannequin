package industries.laser.mannequin

import android.view.View
import industries.laser.mannequin.validators.Validator

class MannequinValidated<T>(val view: View) {
    var event: ValidationEvent? = null
    var validator: Validator<T>? = null
    var notifier: ((View, Boolean) -> Unit)? = null
    var isValid: Boolean = true

    infix fun during(validationEvent: ValidationEvent): MannequinValidated<T> {
        this.event = validationEvent
        return this
    }

    infix fun via(function: T.() -> Boolean): MannequinValidated<T> {
        this.validator = function
        return this
    }

    infix fun notifies(function: (View, Boolean) -> Unit): MannequinValidated<T> {
        this.notifier = function
        return this
    }
}
