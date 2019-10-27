package industries.laser.mannequin

import android.widget.EditText
import industries.laser.mannequin.validators.None
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MannequinTest {

    @MockK
    lateinit var editText: EditText

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testRegister() {
        val mannequin = Mannequin()
    }
}