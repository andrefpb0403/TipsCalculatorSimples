package com.example.tipscalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Lógica do botão calcular + teste para que o app não dê crash e avise o usuário que é necessário preencher todos os campos
        binding.btnCalculate.setOnClickListener {
            val totalTableTemp = binding.tieTotalAmount.text
            val numPeopleTemp = binding.tieNumPeople.text
            val percentageTemp = binding.tiePercentage.text


            if (totalTableTemp?.isEmpty() == true ||
                numPeopleTemp?.isEmpty() == true ||
                percentageTemp?.isEmpty() == true
            ) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val totalTable: Float = totalTableTemp.toString().toFloat()
                val nPeople: Int = numPeopleTemp.toString().toInt()
                val percentage: Int = percentageTemp.toString().toInt()


                //Lógica da conta:
                // Total da mesa dividido pelo numero de pessoa
                //o resultado da divisão multiplica pelo percentual de gorjeta escolhido e divide por 100
                //com o resultado da primeira divisão se soma com o resultado da segunda divisão
                val totalTemp = totalTable / nPeople
                val tips = (totalTemp * percentage) / 100
                val totalWithTips = totalTemp + tips

                val intent = Intent(this, SummaryActivity::class.java)
                intent.apply {
                    //Salvando os dados da primeira Activity para recuperar na segunda activity
                    putExtra("totalAmount", totalTable)
                    putExtra("numPeople", nPeople)
                    putExtra("percentage", percentage)
                    putExtra("totalWithTips", totalWithTips)
                }
                clean()
                startActivity(intent)
            }
        }
        //Lógica do botão limpar, limpa todos os editáveis na tela do usuário
        binding.btnClean.setOnClickListener {
            clean()
        }
    }

    fun clean() {
        binding.tieTotalAmount.setText("")
        binding.tiePercentage.setText("")
        binding.tieNumPeople.setText("")
    }
}