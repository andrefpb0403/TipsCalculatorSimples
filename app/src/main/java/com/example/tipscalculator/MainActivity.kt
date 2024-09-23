package com.example.tipscalculator

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tipscalculator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        var percentage: Int = 0
        binding.rbPercentage10.setOnCheckedChangeListener { _, isChecked ->
            println("Andre1 " + isChecked)
            if (isChecked) {
                percentage = 10
            }
        }
        binding.rbPercentage15.setOnCheckedChangeListener { _, isChecked ->
            println("Andre2 " + isChecked)
            if (isChecked) {
                percentage = 15
            }
        }
        binding.rbPercentage20.setOnCheckedChangeListener { _, isChecked ->
            println("Andre3 " + isChecked)
            if (isChecked) {
                percentage = 20
            }
        }
        val adapter = ArrayAdapter.createFromResource(this, R.array.num_people, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPeople.adapter = adapter

        var numbOfPeopleSelected = 0
        binding.spinnerPeople.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                numbOfPeopleSelected = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        //Lógica do botão calcular + teste para que o app não dê crash e avise o usuário que é necessário preencher todos os campos
        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotalAmount.text


            if (totalTableTemp?.isEmpty() == true) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numbOfPeopleSelected

                //Lógica da conta:
                // Total da mesa dividido pelo numero de pessoa
                //o resultado da divisão multiplica pelo percentual de gorjeta escolhido e divide por 100
                //com o resultado da primeira divisão se soma com o resultado da segunda divisão
                val totalTemp = totalTable / nPeople
                val tips = totalTemp * percentage / 100
                val totalWithTips = totalTemp + tips
                binding.tvResult.text = "Total with tips: $totalWithTips"
                println("Andre1 " + totalWithTips)
            }
        }

        //Lógica do botão limpar, limpa todos os editáveis na tela do usuário
        binding.btnClean.setOnClickListener {
            binding.tvResult.text = ""
            binding.tieTotalAmount.setText("")
            binding.rbPercentage10.isChecked = false
            binding.rbPercentage15.isChecked = false
            binding.rbPercentage20.isChecked = false

        }
    }
}