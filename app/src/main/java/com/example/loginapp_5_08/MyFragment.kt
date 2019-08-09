package com.example.loginapp_5_08

import android.content.Intent
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

                val clickintent = Intent(activity, HomeScreen::class.java)
                startActivity(clickintent)

                activity?.finish()

                /*
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
                */
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

        //Valid Length
        if (!validLength(str.length))
        {
            valid = false
        }

        //atleast one number
        if (!validNumber(str))
        {
            valid = false
        }

        //atleast one capital
        if (!validCapital(str))
        {
            valid = false
        }

        //atleast one small
        if (!validSmall(str))
        {
            valid = false
        }

        //atleast one special
        if (!validSpecial(str))
        {
            valid = false
        }

        return valid
    }

    fun validLength(len : Int) : Boolean
    {
        // Password should be minimum minimum 8 characters long
        if (len < 6) {
            return false
        }
        return true
    }

    fun validNumber(str: String) : Boolean
    {
        // Password should contain at least one number
        val exp = ".*[0-9].*"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    fun validCapital(str: String) : Boolean
    {
        // Password should contain at least one capital letter
        val exp = ".*[A-Z].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    fun validSmall(str: String) : Boolean
    {
        // Password should contain at least one small letter
        val exp = ".*[a-z].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    fun validSpecial(str: String) : Boolean
    {
        // Password should contain at least one special character
        // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
        val exp = ".*[~!@#\$%\\^&*()\\-_=+\\|\\[{\\]};:'\",<.>/?].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }
}