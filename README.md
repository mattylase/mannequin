# Mannequin

Mannequin is a form validataion library built with expressiveness in mind. Using Kotlin's infix
functions, the goal is to provide a cleanly read and obvious api.

```kotlin
override fun onCreate() {
    ...
    
    val mannequin = Mannequin()

    mannequin validates userNameEditText during OnKey via MinLength(4)
    mannequin validates emailEditText during OnKey via Email
    mannequin validates descEditText during OnFocusLost via MaxLength(200) notifies ::onResult

    mannequin.watch()
    
    ...
}

fun onResult(view: View, valid: Boolean) {
    //  update states based on validity...
}

```
