package nanicky.losties.android.core.ui

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import nanicky.losties.android.R

class EmailTextChangeListener(val view: EditText) : TextWatcher {
    override fun onTextChanged(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
    }

    override fun beforeTextChanged(
        s: CharSequence,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun afterTextChanged(editable: Editable) {
        checkEmail(view)
    }

    private fun checkEmail(edt: EditText) {
        if (!isEmailValid(edt.text.toString())) {
            edt.error = edt.context.getString(R.string.input_error)
        }
    }

    private fun isEmailValid(email: CharSequence): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}