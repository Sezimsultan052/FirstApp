package com.example.firstapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.firstapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initValue()
        initListener()

        }

    private fun initListener() {
        binding.btnSecond.setOnClickListener{
            val secondValue = binding.etSecond.text.toString()
            if (secondValue.isNotBlank()){
                openFirstActivity(secondValue)
            } else {
                makeToast(this, getString(R.string.empty_field))
            }
        }
    }

    private fun openFirstActivity(secondValue: String) {
        val intent = Intent()
        intent.putExtra("secondValue", secondValue)
        setResult(RESULT_OK, intent)
        finish()

    }

    private fun makeToast(context: Context, message:  String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

   

    private fun initValue() {
        val extras: Bundle? = intent.extras
        val setValue = extras?.getString("firstValue")
        binding.etSecond.setText(setValue)
    }

}

