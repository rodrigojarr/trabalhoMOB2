package br.ufpr.trabmob2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class StudentsActivity : AppCompatActivity() {

    companion object {
        val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students)

        val btnGoStudents = findViewById<Button>(R.id.btnGoStudents)
        val btnGoMain = findViewById<Button>(R.id.btnGoMain)
        val radioGroupHouses = findViewById<RadioGroup>(R.id.radioGroupHouses)

        btnGoStudents.setOnClickListener {
            val selectedRadioButtonId = radioGroupHouses.checkedRadioButtonId

            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Selecione uma casa.", Toast.LENGTH_SHORT).show()
            } else {
                val house = when (selectedRadioButtonId) {
                    R.id.radioGryffindor -> "gryffindor"
                    R.id.radioSlytherin -> "slytherin"
                    R.id.radioHufflepuff -> "hufflepuff"
                    R.id.radioRavenclaw -> "ravenclaw"
                    else -> ""
                }

                val intent = Intent(this, DetailsStudentsActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE, house)
                startActivity(intent)
            }
        }

        btnGoMain.setOnClickListener {
            finish()
        }
    }
}
