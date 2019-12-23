package industries.laser.mannequin

import android.view.View
import industries.laser.mannequin.validators.Validator

class MannequinValidated<T>(val view: View) {
    internal var validationFunction: ValidationFunction = null
    internal var isValid: Boolean = true
    var event: ValidationEvent? = null
    var validator: Validator<T>? = null
    var notifier: ((View, Boolean) -> Unit)? = null

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

typealias ValidationFunction = (() -> Unit)?
