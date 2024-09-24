package com.example.tipscalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipscalculator.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Concatenando os valores e os recuperando para utilizar nas views desta activity
        val totalAmount = intent.getFloatExtra("totalAmount", 0.0f)
        val numPeople = intent.getIntExtra("numPeople", 0)
        val percentage = intent.getIntExtra("percentage", 0)
        val totalWithTips = intent.getFloatExtra("totalWithTips", 0.0f)



        binding.tvTotalAmount.text = "R$ %.2f".format(totalAmount)
        binding.tvNumPeople.text = "$numPeople pessoas"
        binding.tvPercentage.text = "$percentage %"
        binding.tvTotalPerPerson.text = "R$ %.2f".format(totalWithTips)

        binding.btnRecalculate.setOnClickListener {
            finish()
        }
    }
}