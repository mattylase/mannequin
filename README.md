# Mannequin

Mannequin is a form validataion library built with expressiveness in mind. Using Kotlin's infix
functions, the goal is to provide a cleanly read and obvious api.

```kotlin
val mannequin = Mannequin()

mannequin validates userNameEditText during OnKey via Length(13)
mannequin validates emailEditText during OnKey via Email

mannequin.watch()
```
