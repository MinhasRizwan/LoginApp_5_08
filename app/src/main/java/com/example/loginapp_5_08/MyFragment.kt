package com.example.loginapp_5_08

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_activity.*
import java.util.regex.Pattern

class MyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_activity,
            container, false
        )

        val button: Button = view.findViewById(R.id.button)

/*
        editText1.addTextChangedListener(object:TextWatcher{

            override fun afterTextChanged(editText: EditText) {
                val content = editText.text.toString()
                editText?.error = if (content.length >= 1) null else "Email required"
            }

            override fun beforeTextChanged(editText: EditText) {

            }
            override fun onTextChanged(editText: EditText) {

            }
        })

        editText1.error = if (editText1.text.toString().length >= 6) null else "Minimum length = 6"
*/

/*
        editText1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                //tvSample.setText("Text in EditText : "+s)
            }
        })

        //editText1.validate("Valid email address required") { s -> s.isValidEmail() }
*/

        button.setOnClickListener {showDialOk()}


        return view
    }
/*
    fun String.isValidEmail(): Boolean
            = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }
*/
    fun showDialOk(){

        val email = editText1.editableText.toString()
        val pass = editText2.editableText.toString()

        Toast.makeText(activity, "Logging", Toast.LENGTH_SHORT).show()

        if (isEmailValid(email))
        {
            //view.findViewById<EditText>(R.id.editText1).setError( "First name is required!" )
            Toast.makeText(activity, "Valid Email", Toast.LENGTH_SHORT).show()

            if (isValidPassword(pass))
            {
                Toast.makeText(activity, "Valid Password", Toast.LENGTH_SHORT).show()

                // build alert dialog
                val dialogBuilder = android.app.AlertDialog.Builder(getActivity())

                // set message of alert dialog
                dialogBuilder.setMessage("Logging in...")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Ok", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Logging In")
                // show alert dialog
                alert.show()

            }
            else
            {

                Toast.makeText(activity, " In valid Password", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            Toast.makeText(activity, " In valid Email", Toast.LENGTH_SHORT).show()
        }

    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(str: String): Boolean {

        var valid: Boolean = true

        // Password policy check
        // Password should be minimum minimum 8 characters long
        if (str.length < 6) {
            valid = false
        }

        // Password should contain at least one number
        var exp = ".*[0-9].*"

        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        // Password should contain at least one capital letter
        exp = ".*[A-Z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        // Password should contain at least one small letter
        exp = ".*[a-z].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)
        if (!matcher.matches()) {
            valid = false
        }

        // Password should contain at least one special character
        // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
        exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            valid = false
        }

        return valid
    }
}