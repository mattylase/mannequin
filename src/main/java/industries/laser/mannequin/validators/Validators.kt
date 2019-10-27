package industries.laser.mannequin.validators

import android.util.Log

typealias Validator<T> = (T) -> Boolean

/* String Validators */
val None: Validator<Any> = { true }
val Wrong: Validator<Any> = { false }
val Test: Validator<String> = { input ->
    Log.d("Test", input)
    true
}
