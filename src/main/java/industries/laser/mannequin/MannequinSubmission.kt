package industries.laser.mannequin

import android.view.View

class MannequinSubmission(val mannequin: Mannequin): View.OnClickListener {
    lateinit var submitView: View

    override fun onClick(view: View?) {
        mannequin.onSubmit()
    }

    internal fun registerSubmitView(button: View) {
        submitView = button
        submitView.setOnClickListener(this)
    }

}
