package com.example.loginapp_5_08

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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

        button.setOnClickListener {v: View -> showDialOk(v)}

        return view
    }

    fun showDialOk(view : View){

        val email = editText1.editableText.toString()
        val pass = editText2.editableText.toString()

        Toast.makeText(activity, "Logging", Toast.LENGTH_SHORT).show()

        if (isEmailValid(email))
        {
            //view.findViewById<EditText>(R.id.editText1).setError( "First name is required!" )
            validtext1.visibility = View.INVISIBLE

            if (isValidPassword(pass))
            {
                validtext2.visibility = View.INVISIBLE

                // build alert dialog
                val dialogBuilder = android.app.AlertDialog.Builder(getActivity())

                // set message of alert dialog
                dialogBuilder.setMessage("Logging in...")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Ok", {
                            dialog, _ -> dialog.cancel()
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
                validtext2.visibility = View.VISIBLE
                //Toast.makeText(activity, " In valid Password", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            validtext1.visibility = View.VISIBLE
        }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(str: String): Boolean {

        var valid = true

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