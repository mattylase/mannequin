package industries.laser.mannequin.validators

import android.util.Log
import industries.laser.mannequin.Mannequin

typealias Validator<T> = (T) -> Boolean

/* Test and debug validators */
val Right: Validator<Any> = { true }
val Wrong: Validator<Any> = { false }
val Debug: Validator<String> = { input ->
    Log.d(Mannequin.TAG, input)
    true
}

/* String Validators */
val Email: Validator<String> = { input ->
    input.matches("^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,4}".toRegex())
}
fun MaxLength(limit: Int): Validator<String> {
    return {
        it.length <= limit
    }
}
fun MinLength(limit: Int): Validator<String> {
    return {
        it.length >= limit
    }
}

/* CheckBox Validators */
fun Checked(shouldBeChecked: Boolean): Validator<Boolean> {
    return {
        it == shouldBeChecked
    }
}