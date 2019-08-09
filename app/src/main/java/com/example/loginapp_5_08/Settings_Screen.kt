package com.example.loginapp_5_08

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_settings__screen.*

class Settings_Screen : AppCompatActivity(){

    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings__screen)

        setSupportActionBar(findViewById(R.id.toolbar))

        sharedPreferences =  this.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        if (savedInstanceState != null)
        {
            dropdownScale.setSelection(sharedPreferences.getInt("temp scale",1))
        }
        //dropdownScale.setSelection(sharedPreferences.getInt("temp scale",0))

        val spinner: Spinner = findViewById(R.id.dropdownScale)

        // ArrayAdapter (string array and a default spinner layout)
        ArrayAdapter.createFromResource(
            this,
            R.array.temp_scale,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            // layout (use when the list of choices appears)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // adapter to the spinner
            spinner.setAdapter(adapter)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                editor.putInt("temp scale",position)
                editor.commit()

                Toast.makeText(this@Settings_Screen, "Temperature Scale:" + parent.getItemAtPosition(position) , Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkedTextView -> {
                    if (checked) {
                        Toast.makeText(this, "Location : Checked", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Location : Un-Checked", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun checkStatus(view: View) {
        Toast.makeText(this, "Temperature Scale: " + dropdownScale.selectedItem.toString(), Toast.LENGTH_LONG).show()
        Toast.makeText(this, "Current City: " + checkedTextView.isChecked.toString(), Toast.LENGTH_LONG).show()
    }
}
