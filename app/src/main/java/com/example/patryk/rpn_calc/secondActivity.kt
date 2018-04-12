package com.example.patryk.rpn_calc

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class secondActivity : AppCompatActivity(), OnSeekBarChangeListener, AdapterView.OnItemSelectedListener{

    var colors = arrayOf("Original", "White", "Blue", "Green", "Red", "Yellow")
    var buttonsColor = colors[0]
    var textViewColor = colors[0]
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (p0) {
            spinner -> {
                buttonsColor = colors[p2]
                //Toast.makeText(this@secondActivity, "You have Selected spinner - buttonsColor: " + buttonsColor, Toast.LENGTH_SHORT).show()
            }
            spinner2 -> {
                textViewColor = colors[p2]
                //Toast.makeText(this@secondActivity, "You have Selected spinner2 - textViewColor: " + textViewColor, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        progressView.text = p1.toString()
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        seekBar!!.setOnSeekBarChangeListener(this)
        spinner!!.setOnItemSelectedListener(this)
        spinner2!!.setOnItemSelectedListener(this)
        val extras = intent.extras ?: return
        val  numberOfDigits = extras.getString("numberOfDigits")
        buttonsColor = extras.getString("buttonsColor")
        textViewColor= extras.getString("textViewColor")
        progressView.text = numberOfDigits
        seekBar.progress = numberOfDigits.toInt()

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner!!.setAdapter(aa)
        spinner2!!.setAdapter(aa)
        spinner.setSelection(aa.getPosition(buttonsColor))
        spinner2.setSelection(aa.getPosition(textViewColor))
    }

    override fun finish() {
        val data = Intent()
        data.putExtra("numberOfDigits",  seekBar.progress.toString())
        data.putExtra("buttonsColor",  buttonsColor)
        data.putExtra("textViewColor",  textViewColor)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }
}




