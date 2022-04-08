package com.example.firstapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firstapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var launch: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {  setContentView(it.root) }


        initClick()
        initLaunch()
    }

    private fun initLaunch() {
        launch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val intent: Intent?= it.data
                val secondValue  = intent?.getStringExtra("secondValue")
                binding.etMain.setText(secondValue)

            }
        }
    }

    private fun initClick() {
        binding.btnSend.setOnClickListener{
            val firstValue: String = binding.etMain.text.toString()
            if (firstValue.isBlank()){
                makeToast(this)
            } else {
                openSecondActivity(firstValue)
            }
        }
    }

    private fun openSecondActivity(firstValue: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("firstValue", firstValue)
        launch.launch(intent)

    }

    private fun makeToast(context: Context) {
        Toast.makeText(context, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
    }
}