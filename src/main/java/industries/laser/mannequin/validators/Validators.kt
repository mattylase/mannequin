package industries.laser.mannequin.validators

import industries.laser.mannequin.MannequinValidated

typealias Validator = (MannequinValidated) -> Boolean

//class None: Validator { override fun validate(mannequinValidated: MannequinValidated) = true }
fun None(mannequinValidated: MannequinValidated) = true

